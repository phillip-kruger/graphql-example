package com.github.phillipkruger.user.client;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import io.smallrye.graphql.client.GraphQLClient;
import io.smallrye.graphql.client.Response;
import io.smallrye.graphql.client.dynamic.api.DynamicGraphQLClient;
import java.math.BigInteger;
import javax.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Main app to do person operations
 * @author Phillip Kruger (phillip.kruger@redhat.com)
 */
@QuarkusMain
public class PersonMain implements QuarkusApplication {

    @Inject
    PersonGraphQLClient graphQLClient;
    
    @Inject @RestClient
    PersonRestClient restClient;
    
    @Inject
    @GraphQLClient("person-dynamic")    
    DynamicGraphQLClient graphQLDynamicClient;
    
    @Override
    public int run(String... args) throws Exception {
        BigInteger id = getRequestedPersonId(args);
        
        System.err.println("================ REST ================");
        Person restPerson = restClient.getPerson(id);
        System.err.println(restPerson);
      
        System.err.println("================ GRAPHQL Typesafe ================");
        Person graphQlPerson = graphQLClient.getPerson(id);
        System.err.println(graphQlPerson);
        
        System.err.println("================ GRAPHQL Dynamic ================");
        
        String personQuery = """
            query Person($id: BigInteger = 1){
                person(id:$id){
                  id
                  title
                  names
                  surname
                  scores {
                    name
                    value
                  }
                }
              }""";
        
        Map<String,Object> vars = new HashMap<>();
        vars.put("id",1);
        
        Response response = graphQLDynamicClient.executeSync(personQuery,vars);
        Person graphQLDynamicPerson = response.getObject(Person.class, "person");
        System.err.println(graphQLDynamicPerson);
        
        return 0;
    }
    
    private BigInteger getRequestedPersonId(String... args){
        BigInteger id = new BigInteger("1");
        if(args.length!=0){
            id = new BigInteger(args[0]);
        }
        return id;
    }
}