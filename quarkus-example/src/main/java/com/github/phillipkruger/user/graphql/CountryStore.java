package com.github.phillipkruger.user.graphql;

import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class CountryStore {
    
    Map<String,String> cache = new HashMap<>();
    
    public String getCountry(String locale){
        if(cache.containsKey(locale)){
            System.err.println("Country: returning [" + locale + "] from cache");
            return cache.get(locale);
        }else{
            String country = fetchCountry(locale);
            cache.put(locale,country);
            System.err.println("Country: cache size [" + cache.size() + "]");
            return country;
        }          
    }
    
    private String fetchCountry(String locale){
        System.err.println("Country: fetching [" + locale + "] from database");
        // Pretend fetch from DB
        if(locale.equals("en-ZA")){
            return "South Africa";
        }else if(locale.equals("en-AU")){
            return "Austalia";
        }else if(locale.equals("en-UK")){
            return "United Kingdom";
        }else{
            return "Unknown";
        }
    }
}
