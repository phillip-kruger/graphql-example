package com.github.phillipkruger.user.graphql;

import com.github.phillipkruger.user.model.Event;
import com.github.phillipkruger.user.model.Person;
import com.github.phillipkruger.user.model.Profile;
import com.github.phillipkruger.user.model.Score;
import com.github.phillipkruger.user.backend.EventDB;
import com.github.phillipkruger.user.backend.PersonDB;
import com.github.phillipkruger.user.backend.ScoreDB;
import java.util.List;
import javax.inject.Inject;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.DefaultValue;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.graphql.Source;

@GraphQLApi
public class ProfileGraphQLApi {
    
    @Query("profileFull")
    @Description("Get a person's profile using the person's Id (same a the REST service)")
    public Profile getProfileFull(int personId) {
        Person person = personDB.getPerson(personId);
        List<Score> scores = scoreDB.getScores(person.getIdNumber());
        Profile profile = new Profile();
        profile.setId(person.getIdNumber());
        profile.setPerson(person);
        profile.setScores(scores);
        return profile;
    }
    
    @Query("profile")
    @Description("Get a person's profile using the person's Id")
    public Profile getProfile(int personId) {
        Person person = personDB.getPerson(personId);
        
        Profile profile = new Profile();
        profile.setId(person.getIdNumber());
        profile.setPerson(person);
        
        return profile;
    }
    
    public List<Score> getScores(@Source Profile profile) {
        Person person = profile.getPerson();
        return scoreDB.getScores(person.getIdNumber());
    }
    
    @Query("person")
    public Person getPerson(@Name("personId") int personId){
        return personDB.getPerson(personId);
    }
    
    public List<Score> getScores(@Source Person person) {
        return scoreDB.getScores(person.getIdNumber());
    }
    
    public List<Score> getScores2(@Source Person person) throws ScoresNotAvailableException {
        throw new ScoresNotAvailableException("Scores for person [" + person.getIdNumber() + "] is not available");
    }
    
    // List Queries 
    
    @Query
    public List<Person> getPeople(){
        return personDB.getPeople();
    }
    
    // Mutations
    
    @Mutation
    public Person updatePerson(Person person){
        return personDB.updatePerson(person);    
    }
    
    @Mutation
    public Person deletePerson(int id){
        return personDB.deletePerson(id);    
    }
    
    // Complex graphs (Source of a source)
    public List<Event> getEvents(@Source Score score) {
        return eventDB.getEvents(score.getId());
    }
    
    // Default values
    @Query
    public List<Person> getPersonsWithSurname(
            @DefaultValue("Kruger") String surname) {
    
        return personDB.getPeopleWithSurname(surname);
    }
    
    @Inject 
    private PersonDB personDB;
    
    @Inject 
    private ScoreDB scoreDB;
    
    @Inject 
    private EventDB eventDB;

}
