package io.github.cjrnew.mscreditappraiser.feignclients;

import io.github.cjrnew.mscreditappraiser.model.ClientData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "ms-clients", path = "/clients")
public interface ClientResourceFeignClient {

    @GetMapping(params = "cpf")
    ResponseEntity<ClientData> getClientData(@RequestParam("cpf") String cpf);
}
