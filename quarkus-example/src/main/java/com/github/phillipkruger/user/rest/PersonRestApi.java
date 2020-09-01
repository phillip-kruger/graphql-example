package com.github.phillipkruger.user.rest;

import com.github.phillipkruger.user.model.Person;
import com.github.phillipkruger.user.service.PersonService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Slf4j
@ApplicationScoped
@Path("/person")
@Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Person service",description = "Person Services")
public class PersonRestApi {

    @Inject
    PersonService personService;

//    @GET
//    @Path("/{id}")
//    @Operation(description = "Get a person using the person's Id")
//    public Person getPerson(@PathParam("id") Long id){
//        return personService.getPerson(id);
//    }

    @GET
    @Operation(description = "Get all people")
    public List<Person> getPeople(){
        return personService.getPeople();
    }

    @POST
    @Transactional
    @Path("/post1")
    public Person post1(@BeanParam Person personContext) {
        log.info("there is something");
        System.out.println("123");
        Person person = personService.getPerson(personContext);
        log.info("there is something: {}", person);
        System.out.println("321");
        return person;
    }

    // with Websocker will be timeout, without websocker will be nullPointerException.
    @POST
    @Transactional
    @Path("/post2")
    public Person post2(InputStream body) throws IOException {
        log.info("there is something");
        String deserializedBody = streamToString(body);
        Person requestOwner = personService.deserializeFromRequest(Person.class, deserializedBody);
        log.info("there is something: {}", deserializedBody);
        System.out.println("111");
        return requestOwner;
    }

    // with Websocker will be timeout, without websocker will be nullPointerException.
    @POST
    @Transactional
    @Path("/post3")
    public Response post3(InputStream body) throws IOException {
        log.info("there is something");
        String deserializedBody = streamToString(body);
        Person requestOwner = personService.deserializeFromRequest(Person.class, deserializedBody);
        log.info("there is something: {}", deserializedBody);
        System.out.println("111");
        return Response.ok(requestOwner).build();
    }

    public static String streamToString(InputStream inputStream) throws IOException {
        log.info("there is something 1");
        try {
            log.info("there is something 2");
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
        } catch (IOException ioe) {
            log.info("there is something 0");
            throw new IOException(ioe);
        }
    }

}