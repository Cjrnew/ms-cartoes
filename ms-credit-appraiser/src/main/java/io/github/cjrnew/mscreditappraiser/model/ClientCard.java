package io.github.cjrnew.mscreditappraiser.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClientCard {

    private String name;
    private String flag;
    private BigDecimal limitReleased;
}
