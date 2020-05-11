package com.github.phillipkruger.user.backend;

import com.github.phillipkruger.user.model.Event;
import com.github.phillipkruger.user.model.Person;
import com.github.phillipkruger.user.model.Score;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;

/**
 * Producing the dummy data in memory
 * @author Phillip Kruger (phillip.kruger@redhat.com)
 */
public class Database {
    private static final Logger log = Logger.getLogger(Database.class.getName());
    
    private Database(){}
    
    public static Map<Integer,Person> getPeopleSchema(){
        log.info("Loading dummy person data...");
        Map<Integer,Person> personDatabase = new HashMap<>();
        try(InputStream jsonStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("META-INF/resources/data/person.json")){
            if(jsonStream!=null){
                
                List<Person> loaded = JSONB.fromJson(jsonStream, new ArrayList<Person>(){}.getClass().getGenericSuperclass());
                for(Person p:loaded){
                    personDatabase.put(p.getId(), p);
                }
            }else{
                log.log(Level.WARNING, "Can not load dummy data [person.json]");
            }
        } catch (IOException ex) {
            log.log(Level.WARNING, "Can not load dummy data [person.json] - {1}", new Object[]{ex.getMessage()});
        }
        
        log.fine("... loaded [" + personDatabase.size() + "] people");
        
        return personDatabase;
    }
 
    public static Map<String,List<Score>> getScoresSchema(){
        log.info("Loading dummy score data...");
        Map<String,List<Score>> scoreDatabase = new HashMap<>();
        try(InputStream jsonStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("META-INF/resources/data/score.json")){
            if(jsonStream!=null){
                List<List<Score>> loaded = JSONB.fromJson(jsonStream, new ArrayList<List<Score>>(){}.getClass().getGenericSuperclass());
                for(List<Score> s:loaded){
                    String personNumber = s.get(0).getPersonNumber();
                    scoreDatabase.put(personNumber, s);
                }
            }else{
                log.log(Level.WARNING, "Can not load dummy data [score.json]");
            }
        } catch (IOException ex) {
            log.log(Level.WARNING, "Can not load dummy data [score.json] - {1}", new Object[]{ex.getMessage()});
        }
        log.fine("... loaded [" + scoreDatabase.size() + "] scores");
        return scoreDatabase;
    }
    
    public static Map<String,List<Event>> getEventsSchema(){
        log.info("Loading dummy event data...");
        Map<String,List<Event>> eventDatabase = new HashMap<>();
        try(InputStream jsonStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("META-INF/resources/data/event.json")){
            if(jsonStream!=null){
                List<List<Event>> loaded = JSONB.fromJson(jsonStream, new ArrayList<List<Event>>(){}.getClass().getGenericSuperclass());
                for(List<Event> s:loaded){
                    String scoreId = s.get(0).getScoreId();
                    eventDatabase.put(scoreId, s);
                }
            }else{
                log.log(Level.WARNING, "Can not load dummy data [event.json]");
            }
        } catch (IOException ex) {
            log.log(Level.WARNING, "Can not load dummy data [event.json] - {1}", new Object[]{ex.getMessage()});
        }
        
        log.warning("... loaded [" + eventDatabase.size() + "] events");
        return eventDatabase;
    }
    
    private static final Jsonb JSONB = JsonbBuilder.create(new JsonbConfig().withFormatting(true));
    
}
