package io.github.cjrnew.mscreditappraiser.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientSituation {

    private ClientData clientData;
    private List<ClientCard> cards;
}
