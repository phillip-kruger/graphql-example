package com.github.phillipkruger.user.backend;

import com.github.phillipkruger.user.model.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PersonDB {
    private final Logger log = Logger.getLogger(PersonDB.class.getName());
    
    private Map<Integer,Person> personDatabase = Database.getPeopleSchema();
    
    public Person getPerson(Integer id){
        log.log(Level.SEVERE, "======= Getting person [{0}] =======", id);    
        return personDatabase.get(id);
    }
    
    public List<Person> getPeople(){
        return new ArrayList<>(personDatabase.values());
    }
    
    public List<Person> getPeopleWithSurname(String surname){
        log.log(Level.SEVERE, "======= Finding people with surname [{0}] =======", surname);
        List<Person> p = new ArrayList<>();
        for(Person person: personDatabase.values()) {
            if(person.getSurname().equalsIgnoreCase(surname))p.add(person);
        }
        return p;
    }
    
    public Person updatePerson(Person person){
        if(person.getId() == null || person.getId() <= 0){
            person.setId(getNextId());
            log.log(Level.SEVERE, "======= Adding person [{0}] =======", person.getId());
        }else{
            log.log(Level.SEVERE, "======= Updating person [{0}] =======", person.getId());
        }
        personDatabase.put(person.getId(), person);
        return person;
    }
    
    public Person deletePerson(Integer id){
        if(personDatabase.containsKey(String.valueOf(id))){
            log.log(Level.SEVERE, "======= Deleting person [{0}] =======", id);
            return personDatabase.remove(String.valueOf(id));
        }
        return null;
    }
    
    private synchronized int getNextId(){
        return personDatabase.size() + 1;
    }
}
