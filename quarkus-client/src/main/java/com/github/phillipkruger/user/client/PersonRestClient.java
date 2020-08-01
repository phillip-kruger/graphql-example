package com.github.phillipkruger.user.client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
public interface PersonRestClient {

    @GET
    @Path("/person/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPerson(@PathParam("id") int id);
}
