package io.github.cjrnew.msclients.domain.dto;

import io.github.cjrnew.msclients.domain.Client;
import lombok.Data;

@Data
public class ClientDTO {

    private String cpf;
    private String name;
    private Integer age;

    public Client toModel(){
        return new Client(cpf, name, age);
    }
}
