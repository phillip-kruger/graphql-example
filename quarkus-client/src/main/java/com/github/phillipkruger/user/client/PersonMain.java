package com.github.phillipkruger.user.client;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import io.smallrye.graphql.client.typesafe.api.GraphQlClientBuilder;
//import javax.inject.Inject;

/**
 * Main app to do person operations
 * @author Phillip Kruger (phillip.kruger@redhat.com)
 */
@QuarkusMain
public class PersonMain implements QuarkusApplication {

    //@Inject
    //PersonGraphQLClient personClient;
    PersonGraphQLClient personClient = GraphQlClientBuilder.newBuilder().build(PersonGraphQLClient.class);
    
    @Override
    public int run(String... args) throws Exception {
        int id = getRequestedPersonId(args);
        Person person = personClient.person(id);
        printPerson(person);
        return 0;
    }
    
    private int getRequestedPersonId(String... args){
        int id = 1;
        if(args.length!=0){
            id = Integer.valueOf(args[0]);
        }
        return id;
    }
    
    private void printPerson(Person person){
        System.out.println("=========================");
        System.out.println("|  " + person.getNames().get(0) + " " + person.getSurname() +"\t|");
        System.out.println("|\t\t\t|");
        for(Score score:person.getScores()){
            System.out.println("|\t " + score.getName() + "\t|");
            System.out.println("|\t " + score.getValue() + "\t\t|");
            System.out.println("|\t\t\t|");
        }
        System.out.println("=========================");
    }
}