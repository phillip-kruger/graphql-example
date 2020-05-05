package com.github.phillipkruger.user.model;

import java.util.UUID;

public class Score implements Measurable {
    private UUID id;
    private ScoreType name;
    private Long value;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ScoreType getName() {
        return name;
    }

    public void setName(ScoreType name) {
        this.name = name;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
