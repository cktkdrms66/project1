package com.example.proejct1.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum University {


    HANYANG("한양대"),
    BUSAN("부산대"),
    KAIST("카이스트"),
    POSTECH("포스텍"),
    GIST("지스트"),
    SKKU("성균관대"),
    SOOKMYUNG("숙명여대"),
    KOREA("고려대");

    private final String korName;

    University(String korName) {
        this.korName = korName;
    }

    public String korName() {
        return korName;
    }


}