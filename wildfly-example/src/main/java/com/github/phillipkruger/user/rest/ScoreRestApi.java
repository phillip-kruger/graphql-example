package com.github.phillipkruger.user.rest;

import com.github.phillipkruger.user.model.Score;
import com.github.phillipkruger.user.service.ScoreService;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/score")
@Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Score service",description = "Score Services")
public class ScoreRestApi {
    
    @Inject 
    private ScoreService scoreService;
    
    @GET 
    @Path("/{idNumber}")
    @Operation(description = "Get a person's scores using the person's Id Number")
    public List<Score> getScores(@PathParam("idNumber") String idNumber){
        return scoreService.getScores(idNumber);
    } 
}