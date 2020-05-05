package com.github.phillipkruger.user.backend;

import com.github.phillipkruger.user.model.Event;
import com.github.phillipkruger.user.model.Person;
import com.github.phillipkruger.user.model.Score;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;

/**
 * Producing the dummy data in memory
 * @author Phillip Kruger (phillip.kruger@redhat.com)
 */
@ApplicationScoped
public class DataProducer {
    private final Logger log = Logger.getLogger(DataProducer.class.getName());
            
    @Produces
    public Map<String,Person> producePeople(){
        log.info("Loading dummy person data...");
        Map<String,Person> personDatabase = new ConcurrentHashMap<>();
        try(InputStream jsonStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("data/person.json")){
            if(jsonStream!=null){
                personDatabase = JSONB.fromJson(jsonStream, new ConcurrentHashMap<String,Person>(){}.getClass().getGenericSuperclass());
            }else{
                log.log(Level.WARNING, "Can not load dummy data [person.json]");
            }
        } catch (IOException ex) {
            log.log(Level.WARNING, "Can not load dummy data [person.json] - {1}", new Object[]{ex.getMessage()});
        }
        
        log.fine("... loaded [" + personDatabase.size() + "] people");
        
        return personDatabase;
    }
 
    @Produces
    public Map<String,List<Score>> produceScores(){
        log.info("Loading dummy score data...");
        Map<String,List<Score>> scoreDatabase = new ConcurrentHashMap<>();
        try(InputStream jsonStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("data/score.json")){
            if(jsonStream!=null){
                scoreDatabase = JSONB.fromJson(jsonStream, new ConcurrentHashMap<String,List<Score>>(){}.getClass().getGenericSuperclass());
            }else{
                log.log(Level.WARNING, "Can not load dummy data [score.json]");
            }
        } catch (IOException ex) {
            log.log(Level.WARNING, "Can not load dummy data [score.json] - {1}", new Object[]{ex.getMessage()});
        }
        log.fine("... loaded [" + scoreDatabase.size() + "] scores");
        return scoreDatabase;
    }
    
    @Produces
    public Map<UUID,List<Event>> produceEvents(){
        log.info("Loading dummy event data...");
        Map<UUID,List<Event>> eventDatabase = new ConcurrentHashMap<>();
        try(InputStream jsonStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("data/event.json")){
            if(jsonStream!=null){
                eventDatabase = JSONB.fromJson(jsonStream, new ConcurrentHashMap<UUID,List<Event>>(){}.getClass().getGenericSuperclass());
            }else{
                log.log(Level.WARNING, "Can not load dummy data [event.json]");
            }
        } catch (IOException ex) {
            log.log(Level.WARNING, "Can not load dummy data [event.json] - {1}", new Object[]{ex.getMessage()});
        }
        
        log.fine("... loaded [" + eventDatabase.size() + "] events");
        return eventDatabase;
    }
    
    private static final Jsonb JSONB = JsonbBuilder.create(new JsonbConfig().withFormatting(true));
    
}
