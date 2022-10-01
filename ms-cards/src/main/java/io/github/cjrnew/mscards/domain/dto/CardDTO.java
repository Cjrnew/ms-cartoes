package io.github.cjrnew.mscards.domain.dto;

import io.github.cjrnew.mscards.domain.Card;
import io.github.cjrnew.mscards.domain.enums.CardFlag;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardDTO {

    private String name;
    private CardFlag flag;
    private BigDecimal income;
    private BigDecimal limit;

    public Card toModel(){
        return new Card(name, flag, income, limit );
    }
}
