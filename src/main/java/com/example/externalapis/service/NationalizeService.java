package com.example.externalapis.service;

import com.example.externalapis.dto.Agify;
import com.example.externalapis.dto.Nationalize;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Service
public class NationalizeService {

    public Mono<Nationalize> getNationalizeForName(String name) {
        WebClient client = WebClient.create();
        Mono<Nationalize> nationalized = client.get()
                .uri("https://api.nationalize.io/?name="+name)
                .retrieve()
                .bodyToMono(Nationalize.class);
        return nationalized;
    }

}
