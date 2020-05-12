package com.github.phillipkruger.user.client;

import javax.enterprise.context.ApplicationScoped;

/**
 * Facade on the person service
 * @author Phillip Kruger (phillip.kruger@redhat.com)
 */
@ApplicationScoped
public class PersonStub {

    public String ping(){
        return "pong";
    }
}
