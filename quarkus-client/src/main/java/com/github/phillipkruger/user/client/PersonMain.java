package com.github.phillipkruger.user.client;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import io.smallrye.graphql.client.typesafe.api.GraphQlClientBuilder;
import javax.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/**
 * Main app to do person operations
 * @author Phillip Kruger (phillip.kruger@redhat.com)
 */
@QuarkusMain
public class PersonMain implements QuarkusApplication {

    //@Inject
    PersonGraphQLClient graphQLClient = GraphQlClientBuilder.newBuilder().build(PersonGraphQLClient.class);
    
    @Inject @RestClient
    PersonRestClient restClient;
    
    @Override
    public int run(String... args) throws Exception {
        int id = getRequestedPersonId(args);
        Person restPerson = restClient.getPerson(id);
        
        System.err.println("================ REST ================");
        System.err.println(restPerson);
      
        Person graphQlPerson = graphQLClient.getPerson(id);
        
        System.err.println("================ GRAPHQL ================");
        System.err.println(graphQlPerson);
        
        return 0;
    }
    
    private int getRequestedPersonId(String... args){
        int id = 1;
        if(args.length!=0){
            id = Integer.valueOf(args[0]);
        }
        return id;
    }
}