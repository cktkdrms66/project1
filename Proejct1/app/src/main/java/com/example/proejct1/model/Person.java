package com.example.proejct1.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {
    private String name;
    private String img;
    private University university;
    private int age;
    private Sex sex;
    private String url;

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public University getUniversity() {
        return university;
    }

    public int getAge() {
        return age;
    }

    public Sex getSex() {
        return sex;
    }

    public String getUrl() {
        return url;
    }

    @JsonCreator
    public Person(
            @JsonProperty("name") String name,
            @JsonProperty("img") String img,
            @JsonProperty("university") University university,
            @JsonProperty("age") int age,
            @JsonProperty("sex") Sex sex,
            @JsonProperty("url") String url
    ) {
        this.name = name;
        this.img = img;
        this.university = university;
        this.age = age;
        this.sex = sex;
        this.url = url;
    }
}
