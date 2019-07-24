package com.github.phillipkruger.user.api;

import com.github.phillipkruger.user.Person;
import com.github.phillipkruger.user.Profile;
import com.github.phillipkruger.user.Score;
import com.github.phillipkruger.user.backend.PersonDB;
import com.github.phillipkruger.user.backend.ScoreDB;
import java.util.List;
import javax.inject.Inject;
import org.eclipse.microprofile.graphql.Argument;
import org.eclipse.microprofile.graphql.DefaultValue;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.graphql.Source;

@GraphQLApi
public class ProfileGraphQLApi {
    
    @Inject 
    private PersonDB personDB;
    
    @Inject 
    private ScoreDB scoreDB;
    
    @Query("profileFull")
    public Profile getProfileFull(@Argument("personId") int personId) {
        Person person = personDB.getPerson(personId);
        List<Score> scores = scoreDB.getScores(person.getIdNumber());
        Profile profile = new Profile();
        profile.setId(person.getIdNumber());
        profile.setPerson(person);
        profile.setScores(scores);
        return profile;
    }
    
    @Query("profile")
    public Profile getProfile(@Argument("personId") int personId) {
        Person person = personDB.getPerson(personId);
        
        Profile profile = new Profile();
        profile.setId(person.getIdNumber());
        profile.setPerson(person);
        
        return profile;
    }
    
    @Query("scores")
    public List<Score> getScores(@Source Profile profile) {
        Person person = profile.getPerson();
        return scoreDB.getScores(person.getIdNumber());
    }
    
    @Query("person")
    public Person getPerson(@Argument("personId") int personId){
        return personDB.getPerson(personId);
    }
    
    @Query("scores")
    public List<Score> scores(@Source Person person) {
        return scoreDB.getScores(person.getIdNumber());
    }
    
    // Other 
    
    @Query("people")
    public List<Person> getPeople(){
        return personDB.getPeople();
    }
    
    @Mutation("updatePerson")
    public Person updatePerson(@Argument("person") Person person){
        return personDB.updatePerson(person);    
    }
    
    @Mutation("deletePerson")
    public Person deletePerson(@Argument("id") int id){
        return personDB.deletePerson(id);    
    }
    
    // Default values
    @Query
    public List<Person> personsWithSurname(@Argument("surname")
                              @DefaultValue("Kruger")
                              String surname) {
    
        return personDB.getPeopleWithSurname(surname);
    }

}