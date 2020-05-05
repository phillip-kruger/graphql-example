package com.github.phillipkruger.user.model;

public class Weight implements Measurable {
    private Long value;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}