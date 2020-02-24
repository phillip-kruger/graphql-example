package com.github.phillipkruger.user.backend;

import com.github.javafaker.Faker;
import com.github.phillipkruger.user.Action;
import com.github.phillipkruger.user.Event;
import com.github.phillipkruger.user.Score;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import lombok.extern.java.Log;

@Log
@ApplicationScoped
public class EventDB {
    
    private final Map<UUID,List<Event>> DB = new ConcurrentHashMap<>();
    
    public List<Event> getEvents(UUID id){
        log.log(Level.SEVERE, "======= Getting events [{0}] =======", id.toString());
        return DB.get(id);
    }
    
    public void init(@Observes Score score){
        switch (score.getName()) {
            case Driving:
                DB.put(score.getId(), createRandomEvents(Action.Drive));
                break;
            case Financial:
                DB.put(score.getId(), createRandomEvents(Action.Save));
                break;
            case Fitness:
                DB.put(score.getId(), createRandomEvents(Action.Gym));
                DB.put(score.getId(), createRandomEvents(Action.Steps));
                break;
            case Activity:
                DB.put(score.getId(), createRandomEvents(Action.CheckUp));
                break;
            default:
                break;
        }
    }
    
    private List<Event> createRandomEvents(Action action) {    
        List<Event> events = new ArrayList<>();
        events.add(createRandomEvent(action));
        events.add(createRandomEvent(action));
        return events;
    }
    
    private Event createRandomEvent(Action action){
        Faker faker = new Faker();
        LocalDateTime when = toLocalDateTime(faker.date().past(365, TimeUnit.DAYS));
        return new Event(action, 
                new BigDecimal(faker.number().numberBetween(0, 10000)),
                when,when);
    }
    
    private LocalDateTime toLocalDateTime(Date date){
        return date.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime();
    }
}