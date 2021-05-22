package com.github.phillipkruger.user.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.eclipse.microprofile.graphql.Ignore;

/**
 * Plain Object to represent a header
 * @author Phillip Kruger (phillip.kruger@redhat.com)
 */
public class Header implements Comparable<Header>{

    private String name;
    private Set<String> values = new HashSet<>();

    public Header() {
    }

    public Header(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Ignore
    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getValues() {
        return values;
    }

    @Ignore
    public void setValues(Set<String> values) {
        this.values = values;
    }
    
    public void addValue(String value){
        this.values.add(value);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Header other = (Header) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Header o) {
        return o.name.compareTo(name);
    }
    
    
}
