package com.github.phillipkruger.user.graphql;

import com.github.phillipkruger.user.model.Headers;
import io.vertx.core.MultiMap;
import io.vertx.ext.web.RoutingContext;
import java.util.Map;
import javax.inject.Inject;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;

/**
 * Provide information on the request
 * @author Phillip Kruger (phillip.kruger@redhat.com)
 */
@GraphQLApi
public class RequestInfoApi {

    @Inject 
    RoutingContext routingContext;
    
    @Query("allHeaders")
    public Headers getHeaders(){
        Headers modeledHeaders = new Headers();
        MultiMap headers = routingContext.request().headers();
        for(Map.Entry<String, String> header:headers){
            modeledHeaders.addHeader(header.getKey(), header.getValue());
        }
        return modeledHeaders;
    }
    
}
