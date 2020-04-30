package com.github.phillipkruger.user.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    private String id;
    private Person person;
    private List<Score> scores;
}
