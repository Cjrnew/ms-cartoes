package io.github.cjrnew.mscards.domain.dto;

import io.github.cjrnew.mscards.domain.ClientCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardsForClientsResponseDTO {

    private String name;
    private String flag;
    private BigDecimal limitReleased;

    public static CardsForClientsResponseDTO fromModel(ClientCard model){
        return new CardsForClientsResponseDTO(
                model.getCard().getName(),
                model.getCard().getFlag().toString(),
                model.getLimit()
        );
    }
}
