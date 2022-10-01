package io.github.cjrnew.mscards.service;

import io.github.cjrnew.mscards.domain.ClientCard;
import io.github.cjrnew.mscards.repository.ClientCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientCardService {
    private final ClientCardRepository repository;

    public List<ClientCard> listCardsByCpf(String cpf){
        return repository.findByCpf(cpf);
    }
}
