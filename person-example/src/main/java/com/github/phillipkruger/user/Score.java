package com.github.phillipkruger.user;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score {
    private UUID id;
    private ScoreType name;
    private Long value;
}
