package com.github.phillipkruger.user.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.json.bind.annotation.JsonbDateFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    
    private Action action;
    private BigDecimal value;
    private LocalDateTime dateTime;
    @JsonbDateFormat("dd MMM yyyy 'at' HH:MM")
    private LocalDateTime when;
}
