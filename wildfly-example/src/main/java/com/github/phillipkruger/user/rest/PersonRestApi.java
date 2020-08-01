package com.github.phillipkruger.user.rest;

import com.github.phillipkruger.user.service.PersonService;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/person")
@Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Person service",description = "Person Services")
public class PersonRestApi {
    
    @Inject
    private PersonService personService;
    
    @GET 
    @Path("/{id}")
    @Operation(description = "Get a person using the person's Id")
    public Response getPerson(@PathParam("id") int id){
        return Response.ok(personService.getPerson(id)).build();
    }
    
    @GET
    @Operation(description = "Get all people")
    public Response getPeople(){
        return Response.ok(personService.getPeople()).build();
    }
}