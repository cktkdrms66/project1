package com.example.proejct1.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum Sex {
    MALE("남자"), FEMALE("여자");

    private final String korName;

    Sex(String korName) {
        this.korName = korName;
    }

    public String korName() {
        return korName;
    }

}
