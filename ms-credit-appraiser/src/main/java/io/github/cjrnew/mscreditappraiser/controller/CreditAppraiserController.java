package io.github.cjrnew.mscreditappraiser.controller;

import io.github.cjrnew.mscreditappraiser.excepetion.ClientDataNotFoundException;
import io.github.cjrnew.mscreditappraiser.excepetion.ErrorComunicationMicroservicesException;
import io.github.cjrnew.mscreditappraiser.model.AppraiserData;
import io.github.cjrnew.mscreditappraiser.model.ApprovedCardsAfterEvaluation;
import io.github.cjrnew.mscreditappraiser.model.ClientSituation;
import io.github.cjrnew.mscreditappraiser.service.CreditAppraiserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("credit-appraiser")
@RequiredArgsConstructor
public class CreditAppraiserController {

    private final CreditAppraiserService creditAppraiserService;

    @GetMapping
    public String status() {
        return "ok";
    }

    @GetMapping(value = "client-situation", params = "cpf")
    public ResponseEntity getClientSituation(@RequestParam("cpf") String cpf) {
        try {
            ClientSituation clientSituation = creditAppraiserService.getClientSituation(cpf);
            return ResponseEntity.ok(clientSituation);
        } catch (ClientDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErrorComunicationMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getStatus());
        }
    }

    @PostMapping
    public ResponseEntity doCreditEvaluation(@RequestBody AppraiserData data) {
        try {
            ApprovedCardsAfterEvaluation approvedCardsAfterEvaluation = creditAppraiserService.doEvaluation(data.getCpf(), data.getIncome());
            return ResponseEntity.ok(approvedCardsAfterEvaluation);
        }catch (ClientDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        }catch (ErrorComunicationMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }
}
