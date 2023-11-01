package com.example.externalapis.service;

import com.example.externalapis.dto.Agify;
import com.example.externalapis.dto.Gender;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Service
public class GenderizeService {

    public Mono<Gender> getGenderForName(String name) {
        WebClient client = WebClient.create();
        Mono<Gender> genderized = client.get()
                .uri("https://api.genderize.io/?name="+name)
                .retrieve()
                .bodyToMono(Gender.class);
        return genderized;
    }



}
