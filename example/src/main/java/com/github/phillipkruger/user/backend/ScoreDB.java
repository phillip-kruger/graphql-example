package com.github.phillipkruger.user.backend;

import com.github.javafaker.Faker;
import com.github.phillipkruger.user.Person;
import com.github.phillipkruger.user.Score;
import com.github.phillipkruger.user.ScoreType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import lombok.extern.java.Log;

@Log
@ApplicationScoped
public class ScoreDB {
    
    private final Map<String,List<Score>> DB = new ConcurrentHashMap<>();
    
    public Collection<List<Score>> getScoreValues(){
        log.log(Level.SEVERE, "======= Getting all score values=======");
        return DB.values();
    }
    
    public Set<String> getScoreKeys(){
        log.log(Level.SEVERE, "======= Getting all score keys=======");
        return DB.keySet();
    }
    
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
        Faker faker = new Faker();
        List<Score> scores = new ArrayList<>();
        scores.add(new Score(ScoreType.Driving, Long.valueOf(faker.number().numberBetween(0, 100))));
        scores.add(new Score(ScoreType.Fitness, Long.valueOf(faker.number().numberBetween(0, 100))));
        scores.add(new Score(ScoreType.Activity, Long.valueOf(faker.number().numberBetween(0, 100))));
        scores.add(new Score(ScoreType.Financial, Long.valueOf(faker.number().numberBetween(0, 100))));
        return scores;
    }
}