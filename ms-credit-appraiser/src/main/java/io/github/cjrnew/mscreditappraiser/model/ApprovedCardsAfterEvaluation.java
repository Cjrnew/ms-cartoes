package io.github.cjrnew.mscreditappraiser.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ApprovedCardsAfterEvaluation {
    private List<ApprovedCard> cards;
}
