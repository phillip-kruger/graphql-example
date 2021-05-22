package com.github.phillipkruger.user.client;

import io.smallrye.graphql.client.typesafe.api.GraphQLClientApi;
import org.eclipse.microprofile.graphql.Query;

@GraphQLClientApi
public interface PersonGraphQLClient {
    
    @Query
    public Person getPerson(int id);
    
}
