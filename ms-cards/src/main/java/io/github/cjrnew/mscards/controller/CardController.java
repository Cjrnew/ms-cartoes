package io.github.cjrnew.mscards.controller;

import io.github.cjrnew.mscards.domain.Card;
import io.github.cjrnew.mscards.domain.ClientCard;
import io.github.cjrnew.mscards.domain.dto.CardDTO;
import io.github.cjrnew.mscards.domain.CardsForClientsResponse;
import io.github.cjrnew.mscards.service.CardService;
import io.github.cjrnew.mscards.service.ClientCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;
    private final ClientCardService clientCardService;

    @GetMapping
    public String status(){
        return "ok";
    }

    @PostMapping
    public ResponseEntity registerCard(@RequestBody CardDTO cardDTO ){
        Card card = cardDTO.toModel();
        cardService.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> getCardsIncomeAt(@RequestParam("income") Long income){
        List<Card> list = cardService.getCardsIncomeLessThanOrEqual(income);
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CardsForClientsResponse>> getCardsByClient(
            @RequestParam("cpf") String cpf){
        List<ClientCard> lista = clientCardService.listCardsByCpf(cpf);
        List<CardsForClientsResponse> resultList = lista.stream()
                .map(CardsForClientsResponse::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
    }
}
