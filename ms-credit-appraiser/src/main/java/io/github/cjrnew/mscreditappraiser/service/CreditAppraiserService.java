package io.github.cjrnew.mscreditappraiser.service;

import feign.FeignException;
import io.github.cjrnew.mscreditappraiser.excepetion.ClientDataNotFoundException;
import io.github.cjrnew.mscreditappraiser.excepetion.ErrorComunicationMicroservicesException;
import io.github.cjrnew.mscreditappraiser.feignclients.CardsResourceFeignClient;
import io.github.cjrnew.mscreditappraiser.feignclients.ClientResourceFeignClient;
import io.github.cjrnew.mscreditappraiser.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditAppraiserService {

    private final ClientResourceFeignClient clientResourceFeignClient;
    private final CardsResourceFeignClient cardsResourceFeignClient;

    public ClientSituation getClientSituation(String cpf) throws ClientDataNotFoundException, ErrorComunicationMicroservicesException {
        try {
            ResponseEntity<ClientData> clientDataResponse = clientResourceFeignClient.getClientData(cpf);
            ResponseEntity<List<ClientCard>> cardsResponse = cardsResourceFeignClient.getCardsByClient(cpf);

            return ClientSituation.builder()
                    .clientData(clientDataResponse.getBody())
                    .cards(cardsResponse.getBody())
                    .build();

        }catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw  new ClientDataNotFoundException();
            }
            throw new ErrorComunicationMicroservicesException(e.getMessage(), status);
        }
    }

    public ApprovedCardsAfterEvaluation doEvaluation(String cpf, Long income) throws ClientDataNotFoundException, ErrorComunicationMicroservicesException {
        try {
            ResponseEntity<ClientData> clientDataResponse = clientResourceFeignClient.getClientData(cpf);
            ResponseEntity<List<Card>> cardsResponse = cardsResourceFeignClient.getCardsIncomeAt(income);

            List<Card> cards = cardsResponse.getBody();
            var approvedCardsList = cards.stream().map(card -> {

                ClientData clientData = clientDataResponse.getBody();

                BigDecimal baseLimit = card.getBaseLimit();
                BigDecimal ageBD = BigDecimal.valueOf(clientData.getAge());
                var factor = ageBD.divide(BigDecimal.TEN);
                BigDecimal approvedLimit = factor.multiply(baseLimit);

                ApprovedCard approvedCard = new ApprovedCard();
                approvedCard.setCard(card.getName());
                approvedCard.setFlag(card.getFlag());
                approvedCard.setApprovedLimit(approvedLimit);

                return approvedCard;
            }).collect(Collectors.toList());

            return new ApprovedCardsAfterEvaluation(approvedCardsList);

        }catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw  new ClientDataNotFoundException();
            }
            throw new ErrorComunicationMicroservicesException(e.getMessage(), status);
        }
    }
}
