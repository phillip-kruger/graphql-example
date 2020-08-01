package com.github.phillipkruger.user.graphql;

import com.github.phillipkruger.user.model.Person;
import com.github.phillipkruger.user.model.Score;
import com.github.phillipkruger.user.service.PersonService;
import com.github.phillipkruger.user.service.ScoreService;
import java.util.List;
import javax.inject.Inject;
import org.eclipse.microprofile.graphql.DefaultValue;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.graphql.Source;

@GraphQLApi
public class ProfileGraphQLApi {
    
    @Inject
    private PersonService personService;
    
    @Inject
    private ScoreService scoreService;
    
    // Queries 
    
    @Query("person")
    public Person getPerson(@Name("personId") int personId){
        return personService.getPerson(personId);
    }
    
    public List<Score> getScores(@Source Person person) {
        return scoreService.getScores(person.getIdNumber());
    }
    
    public List<Score> getScores2(@Source Person person) throws ScoresNotAvailableException {
        throw new ScoresNotAvailableException("Scores for person [" + person.getIdNumber() + "] is not available");
    }
    
    // List Queries 
    
    @Query
    public List<Person> getPeople(){
        return personService.getPeople();
    }
    
    // Mutations
    
    @Mutation
    public Person updatePerson(Person person){
        return personService.updatePerson(person);    
    }
    
    @Mutation
    public Person deletePerson(int id){
        return personService.deletePerson(id);    
    }
    
    // Default values
    @Query
    public List<Person> getPersonsWithSurname(
            @DefaultValue("Kruger") String surname) {
    
        return personService.getPeopleWithSurname(surname);
    }
    
}
