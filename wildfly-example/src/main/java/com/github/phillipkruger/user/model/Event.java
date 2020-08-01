package com.github.phillipkruger.user.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.json.bind.annotation.JsonbDateFormat;

public class Event {
    
    private Action action;
    private BigDecimal value;
    private LocalDateTime dateTime;
    @JsonbDateFormat("dd MMM yyyy 'at' HH:MM")
    private LocalDateTime when;
    
    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getWhen() {
        return when;
    }

    public void setWhen(LocalDateTime when) {
        this.when = when;
    }
}
