package com.github.phillipkruger.user.backend;

import com.github.phillipkruger.user.model.Event;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class EventDB {
    private final Logger log = Logger.getLogger(EventDB.class.getName());
    
    @Inject
    Map<UUID,List<Event>> eventDatabase;
    
    public List<Event> getEvents(UUID id){
        log.log(Level.SEVERE, "======= Getting events [{0}] =======", id.toString());
        return eventDatabase.get(id);
    }
}