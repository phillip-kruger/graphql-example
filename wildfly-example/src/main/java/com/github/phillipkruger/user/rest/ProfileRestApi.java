package com.github.phillipkruger.user.rest;

import com.github.phillipkruger.user.model.Person;
import com.github.phillipkruger.user.model.Profile;
import com.github.phillipkruger.user.model.ProfileHateos;
import com.github.phillipkruger.user.model.Score;
import com.github.phillipkruger.user.backend.PersonDB;
import com.github.phillipkruger.user.backend.ScoreDB;
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
@Path("/profile")
@Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Profile service",description = "Profile Services")
public class ProfileRestApi {
    
    @Inject 
    private PersonDB personDB;
    
    @Inject 
    private ScoreDB scoreDB;
    
    @GET 
    @Path("/{personId}")
    @Operation(description = "Get a person's profile using the person's Id")
    public Profile getProfile(@PathParam("personId") int personId){
        
        Person person = personDB.getPerson(personId);
        List<Score> scores = scoreDB.getScores(person.getIdNumber());
        
        Profile profile = new Profile();
        profile.setId(person.getIdNumber());
        profile.setPerson(person);
        profile.setScores(scores);
        
        return profile;
    }
    
    @GET 
    @Path("/hateos/{personId}")
    @Operation(description = "Get a person's profile (hateos) using the person's Id")
    public ProfileHateos getProfileHateos(@PathParam("personId") int personId){
        
        Person person = personDB.getPerson(personId);
        ProfileHateos profile = new ProfileHateos();
        profile.setId(person.getIdNumber());
        profile.setPerson("/rest/person/" + personId);
        profile.setScores("/rest/score/" + person.getIdNumber());
        
        return profile;
    }
    
}