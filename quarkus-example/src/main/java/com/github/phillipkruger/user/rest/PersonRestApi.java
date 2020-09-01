package com.github.phillipkruger.user.rest;

import com.github.phillipkruger.user.model.Person;
import com.github.phillipkruger.user.service.PersonService;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@ApplicationScoped
@Path("/person")
@Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Person service",description = "Person Services")
public class PersonRestApi {

    @Inject
    PersonService personService;

    @GET
    @Path("/{id}")
    @Operation(description = "Get a person using the person's Id")
    public Person getPerson(@PathParam("id") Long id){
        return personService.getPerson(id);
    }

    @GET
    @Operation(description = "Get all people")
    public List<Person> getPeople(){
        return personService.getPeople();
    }
}