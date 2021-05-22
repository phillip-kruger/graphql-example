package com.github.phillipkruger.user.model;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.microprofile.graphql.Ignore;

/**
 * Plain Object to represent a header
 * @author Phillip Kruger (phillip.kruger@redhat.com)
 */
public class Headers {

    private List<Header> headers = new ArrayList<>();
    
    
    public List<Header> getHeaders() {
        return headers;
    }

    @Ignore
    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }
    
    public void addHeader(String name, String value){
        Header h = new Header(name);
        if(headers.contains(h)){
            h = headers.get(headers.indexOf(h));
            h.addValue(value);
        }else{
            h.addValue(value);
            headers.add(h);
        }
    }
    
}
