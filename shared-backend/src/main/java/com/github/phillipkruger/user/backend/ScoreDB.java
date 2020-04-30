package com.github.phillipkruger.user.backend;

import com.github.javafaker.Faker;
import com.github.phillipkruger.user.model.Person;
import com.github.phillipkruger.user.model.Score;
import com.github.phillipkruger.user.model.ScoreType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import lombok.extern.java.Log;

@Log
@ApplicationScoped
public class ScoreDB {
    
    @Inject
    private Event<Score> scoreCreatedEvent;
    
    private final Map<String,List<Score>> DB = new ConcurrentHashMap<>();
    
    public List<Score> getScores(String idNumber){
        log.log(Level.SEVERE, "======= Getting scores [{0}] =======", idNumber);
        return DB.get(idNumber);
    }
    
    public Score getScore(ScoreType type,String idNumber){
        List<Score> scores = getScores(idNumber);
        for(Score score:scores){
            if(score.getName().equals(type))return score;
        }
        return null;
    }
    
    public void init(@Observes Person person){
        DB.put(person.getIdNumber(), createRandomScores());
    }
    
    private List<Score> createRandomScores() {
        
        List<Score> scores = new ArrayList<>();
        scores.add(createRandomScore(ScoreType.Driving));
        scores.add(createRandomScore(ScoreType.Fitness));
        scores.add(createRandomScore(ScoreType.Activity));
        scores.add(createRandomScore(ScoreType.Financial));
        return scores;
    }
    
    private Score createRandomScore(ScoreType type){
        Faker faker = new Faker();
        Score score = new Score(UUID.randomUUID(), type, Long.valueOf(faker.number().numberBetween(0, 100)));
        scoreCreatedEvent.fire(score);
        return score;
    }
}