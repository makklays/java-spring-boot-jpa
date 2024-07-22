package com.techmaxtrix18.validatingforminput;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CityForm {
    @NotNull
    @Size(min=2, max=250)
    private String title;

    /*@NotNull
    @Min(18)
    private Integer age;*/

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /*public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }*/

    public String toString() {
        return "City(Title: " + this.title + ")";
        //return "City(Name: " + this.title + ", Age: " + this.age + ")";
    }
}

