package io.github.cjrnew.mscreditappraiser.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Card {
    private Long id;
    private String name;
    private String flag;
    private BigDecimal baseLimit;
}
