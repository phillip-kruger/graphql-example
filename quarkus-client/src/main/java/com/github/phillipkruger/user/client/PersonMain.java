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
    //PersonStub personStub;
    
    @Override
    public int run(String... args) throws Exception {
        
        PersonStub personStub = GraphQlClientBuilder.newBuilder()
            .build(PersonStub.class);
        
        Person person1 = personStub.person(1);
        
        System.out.println(person1);
        
        return 0;
    }


}
