package com.github.phillipkruger.user.service;

import com.github.phillipkruger.user.model.Person;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.operators.multi.processors.BroadcastProcessor;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
public class PersonService {

    BroadcastProcessor<Person> processor = BroadcastProcessor.create();
    
    @PersistenceContext(name="PersonDS")
    EntityManager em;

    public Person getPerson(Long id){
        System.out.println("======= Getting person [" + id +"] =======");
        return em.find(Person.class,id);
    }

    public Person getPerson(Person person) {
        System.out.println("======= Getting person [" + person +"] =======");
        return em.find(Person.class, person);
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
    public Person updateOrCreate(Person person) {
        if(person.getId()==null){
            em.persist(person);
            processor.onNext(person);
            return person;
        }else{
            Person existing = em.find(Person.class, person.getId());
            if(existing!=null){
                processor.onNext(person);
                return em.merge(person);
            }else {
                em.persist(person);        
                processor.onNext(person);
                return person;
            }
        }
    }

    @Transactional
    public Person delete(Long id) {
        Person p = em.find(Person.class,id);

        if(p!=null){
            em.remove(p);
        }
        return p;
    }

    public Multi<Person> personListener(){
        return processor;
    }
}
