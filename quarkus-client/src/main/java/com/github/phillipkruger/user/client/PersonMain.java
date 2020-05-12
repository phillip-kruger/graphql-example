package com.github.phillipkruger.user.client;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import javax.inject.Inject;

/**
 * Main app to do person operations
 * @author Phillip Kruger (phillip.kruger@redhat.com)
 */
@QuarkusMain
public class PersonMain implements QuarkusApplication {

    @Inject
    PersonStub personStub;
    
    @Override
    public int run(String... args) throws Exception {
        String ping = personStub.ping();
        
        System.out.println(ping);
        
        return 0;
    }


}
