package com.github.phillipkruger.user.client;

import io.smallrye.graphql.client.typesafe.api.GraphQLClientApi;
import java.math.BigInteger;
import org.eclipse.microprofile.graphql.Query;

@GraphQLClientApi
public interface PersonGraphQLClient {
    
    @Query
    public Person getPerson(BigInteger id);
    
}
