package com.example.externalapis.api;

import com.example.externalapis.dto.Agify;
import com.example.externalapis.dto.CombinedResponse;
import com.example.externalapis.dto.Gender;
import com.example.externalapis.dto.Nationalize;
import com.example.externalapis.service.AgifyService;
import com.example.externalapis.service.GenderizeService;
import com.example.externalapis.service.NationalizeService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class DemoController {

    AgifyService agifyService;
    GenderizeService genderizeService;
    NationalizeService nationalizeService;

    public DemoController(AgifyService agifyService, GenderizeService genderizeService, NationalizeService nationalizeService) {
        this.agifyService = agifyService;
        this.genderizeService = genderizeService;
        this.nationalizeService = nationalizeService;
    }

    private final int SLEEP_TIME = 1000*3;

    @GetMapping(value = "/random-string-slow")
    public String slowEndpoint() throws InterruptedException {
        Thread.sleep(SLEEP_TIME);
        return RandomStringUtils.randomAlphanumeric(10);
    }

    @GetMapping(value = "/{name}")
    public Mono<CombinedResponse> statsForName(@PathVariable String name) {
        Mono<Gender> gender = genderizeService.getGenderForName(name);
        Mono<Agify> age = agifyService.getAgifyForName(name);
        Mono<Nationalize> nationality = nationalizeService.getNationalizeForName(name);

        Mono combinedResponseMono = Mono.zip(gender, age, nationality)
                .map(tuple -> new CombinedResponse(tuple.getT1(), tuple.getT2(), tuple.getT3()));

        return combinedResponseMono;
    }

}
