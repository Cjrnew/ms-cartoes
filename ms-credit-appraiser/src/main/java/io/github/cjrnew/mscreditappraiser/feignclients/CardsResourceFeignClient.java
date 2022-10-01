package io.github.cjrnew.mscreditappraiser.feignclients;

import io.github.cjrnew.mscreditappraiser.model.Card;
import io.github.cjrnew.mscreditappraiser.model.ClientCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "ms-cards", path = "/cards")
public interface CardsResourceFeignClient {

    @GetMapping(params = "cpf")
    ResponseEntity<List<ClientCard>> getCardsByClient(@RequestParam("cpf") String cpf);

    @GetMapping(params = "income")
    ResponseEntity<List<Card>> getCardsIncomeAt(@RequestParam("income") Long income);
}
