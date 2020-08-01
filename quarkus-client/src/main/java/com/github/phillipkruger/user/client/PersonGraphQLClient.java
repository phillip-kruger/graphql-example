package com.github.phillipkruger.user.client;

import io.smallrye.graphql.client.typesafe.api.GraphQlClientApi;
import org.eclipse.microprofile.graphql.Query;

@GraphQlClientApi
public interface PersonGraphQLClient {
    
    @Query
    public Person getPerson(int id);
    
}
