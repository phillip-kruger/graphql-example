package com.github.phillipkruger.user.graphql;

import com.github.phillipkruger.user.model.Person;
import com.github.phillipkruger.user.model.Score;
import com.github.phillipkruger.user.service.PersonService;
import com.github.phillipkruger.user.service.ScoreService;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;

import java.util.List;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.eclipse.microprofile.graphql.DefaultValue;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.graphql.Source;

@GraphQLApi
public class PersonGraphQLApi {

    @Inject
    PersonService personService;

    @Inject 
    ScoreService scoreService;
    
    @Query
    @Description("Get a person using the person's Id")
    public Person getPerson(Long id){
        return personService.getPerson(id);
    }

    @Query
    public List<Person> getPeople(){
        return personService.getPeople();
    }
    
    //@RolesAllowed("admin")
    public List<Score> getScores(@Source Person person) throws ScoresNotAvailableException{
        return scoreService.getScores(person.getIdNumber());
        //throw new ScoresNotAvailableException("Scores for person [" + person.getId() + "] not avaialble");
    }
    
    // Batch version of above (only this takes effect when both is present)
//    public List<List<Score>> getScores(@Source List<Person> people) throws ScoresNotAvailableException{
//        List<String> idNumbers = people.stream().map(p -> p.getIdNumber()).collect(Collectors.toList());
//        return scoreService.getScores(idNumbers);
//        //throw new ScoresNotAvailableException("Scores not avaialble");
//    }
    
    // Mutations    
    @Mutation
    public Person updatePerson(Person person){
        return personService.updateOrCreate(person);
    }
    
    @Mutation
    public Person deletePerson(Long id){
        return personService.delete(id);
    }
    
    // Default values
    @Query
    public List<Person> getPersonsWithSurname(
            @DefaultValue("Doyle") String surname) {
        return personService.getPeopleWithSurname(surname);
    }
    
    public GraphQLSchema.Builder leakyAbstraction(@Observes GraphQLSchema.Builder builder) {
        System.err.println(">>>>>>> Here we leak while building the schema");
        // Do what you have to do
        return builder;
    }
    
    public GraphQL.Builder leakyAbstraction(@Observes GraphQL.Builder builder) {
        System.err.println(">>>>>>> Here we leak while building graphQL");
        // Do what you have to do
        return builder;
    }
}
