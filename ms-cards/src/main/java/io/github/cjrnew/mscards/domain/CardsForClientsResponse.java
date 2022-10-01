package io.github.cjrnew.mscards.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardsForClientsResponse {

    private String name;
    private String flag;
    private BigDecimal limitReleased;

    public static CardsForClientsResponse fromModel(ClientCard model){
        return new CardsForClientsResponse(
                model.getCard().getName(),
                model.getCard().getFlag().toString(),
                model.getBaseLimit()
        );
    }
}
