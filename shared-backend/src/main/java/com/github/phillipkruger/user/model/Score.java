package com.github.phillipkruger.user.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score implements Measurable {
    private UUID id;
    private ScoreType name;
    private Long value;
}
