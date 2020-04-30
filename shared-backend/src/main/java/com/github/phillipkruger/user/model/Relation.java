package com.github.phillipkruger.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Relation {
    private RelationType relationType;
    private String personURI;
}
