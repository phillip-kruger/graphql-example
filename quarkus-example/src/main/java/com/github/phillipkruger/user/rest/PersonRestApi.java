package com.github.phillipkruger.user.rest;

import com.github.phillipkruger.user.model.Person;
import com.github.phillipkruger.user.service.PersonService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.transaction.Transactional;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Timed;
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

    @POST
    @Transactional
    @Path("/post1")
    public Person post1(@BeanParam Person personContext) {
        Person person = personService.getPerson(personContext);
        return person;
    }

    // with Websocket will be timeout, without websocker will be nullPointerException.
    @POST
    @Transactional
    @Path("/post2")
    public Person post2(InputStream body) throws IOException {
        String deserializedBody = streamToString(body);
        Person requestOwner = JSONB.fromJson(deserializedBody, Person.class);
        return requestOwner;
    }

    // with Websocket will be timeout, without websocker will be nullPointerException.
    @POST
    @Transactional
    @Path("/post3")
    public Response post3(InputStream body) {
        String deserializedBody = streamToString(body);
        Person requestOwner = JSONB.fromJson(deserializedBody, Person.class);
        return Response.ok(requestOwner).build();
    }

    private String streamToString(InputStream inputStream) {
        try {
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }
    
    private static final Jsonb JSONB = JsonbBuilder.create();
}