package com.github.phillipkruger.user.rest;

import com.github.phillipkruger.user.model.ScoreType;
import com.github.phillipkruger.user.backend.ScoreDB;
import javax.enterprise.context.ApplicationScoped;
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

@ApplicationScoped
@Path("/score")
@Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Score service",description = "Score Services")
public class ScoreRestApi {
    
    @Inject 
    private ScoreDB scoreDB;
    
    @GET 
    @Path("/{idNumber}")
    @Operation(description = "Get a person's scores using the person's Id Number")
    public Response getScores(@PathParam("idNumber") String idNumber){
        return Response.ok(scoreDB.getScores(idNumber)).build();
    }
    
    @GET 
    @Path("/{scoreType}/{idNumber}")
    @Operation(description = "Get a person's score using the person's Id Number given a score name")
    public Response getScores(@PathParam("scoreType") ScoreType scoreType,@PathParam("idNumber") String idNumber){
        return Response.ok(scoreDB.getScore(scoreType,idNumber)).build();
    }
}