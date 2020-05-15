package com.github.phillipkruger.user.client;

import io.smallrye.graphql.client.typesafe.api.GraphQlClientApi;

/**
 * Facade on the person service
 * @author Phillip Kruger (phillip.kruger@redhat.com)
 */
@GraphQlClientApi
public interface PersonStub {
    public Person person(int personId);
}
