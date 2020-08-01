package com.github.phillipkruger.user.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Relation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonbTransient
    private Integer id;
    
    private RelationType relationType;
    private String personURI;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RelationType getRelationType() {
        return relationType;
    }

    public void setRelationType(RelationType relationType) {
        this.relationType = relationType;
    }

    public String getPersonURI() {
        return personURI;
    }

    public void setPersonURI(String personURI) {
        this.personURI = personURI;
    }
}
