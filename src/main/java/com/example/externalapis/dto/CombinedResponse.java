package com.example.externalapis.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CombinedResponse {

    String name;
    String gender;
    double genderProbability;
    int age;
    int ageCount;
    String country;
    double countryProbability;

    public CombinedResponse(Gender gender, Agify agify, Nationalize nationalize) {
        name = agify.getName();
        this.gender = gender.getGender();
        genderProbability = gender.getProbability();
        this.age = agify.getAge();
        ageCount = agify.getCount();
        country = nationalize.getCountryId();
        countryProbability = nationalize.getProbability();
    }
}


