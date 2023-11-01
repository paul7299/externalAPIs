package com.example.externalapis.service;

import com.example.externalapis.dto.Agify;
import com.example.externalapis.dto.Gender;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AgifyService {

    public Mono<Agify> getAgifyForName(String name) {
        WebClient client = WebClient.create();
        Mono<Agify> agified = client.get()
                .uri("https://api.agify.io/?name="+name)
                .retrieve()
                .bodyToMono(Agify.class);
        return agified;
    }

}
