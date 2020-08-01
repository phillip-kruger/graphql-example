package com.github.phillipkruger.user.service;

import com.github.phillipkruger.user.model.Person;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
public class PersonService {
    
    @PersistenceContext(name="PersonDS")
    private EntityManager em;
    
    public Person getPerson(int id){
        return em.find(Person.class,id);
    }
    
    public List<Person> getPeople(){
        return (List<Person>)em.createQuery("SELECT p FROM Person p",Person.class)
					.getResultList();    
    }

    public List<Person> getPeopleWithSurname(String surname) {
        return (List<Person>)em.createQuery("SELECT p FROM Person p WHERE p.surname=:surname",Person.class)
                                        .setParameter("surname", surname)
					.getResultList();    
    }

    @Transactional
    public Person updatePerson(Person person) {
        if(person.getId()==null){
            em.persist(person);
            return person;
        }else{
            return em.merge(person);
        }
        
    }

    @Transactional
    public Person deletePerson(int id) {
        Person p = em.find(Person.class,id);
        if(p!=null){
            em.remove(p);
        }
        return p;
    }
}