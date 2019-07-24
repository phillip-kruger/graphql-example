package com.github.phillipkruger.user.backend;

import com.github.javafaker.Faker;
import com.github.phillipkruger.user.Address;
import com.github.phillipkruger.user.CreditCard;
import com.github.phillipkruger.user.Gender;
import com.github.phillipkruger.user.ImClient;
import com.github.phillipkruger.user.Person;
import com.github.phillipkruger.user.Phone;
import com.github.phillipkruger.user.Relation;
import com.github.phillipkruger.user.RelationType;
import com.github.phillipkruger.user.SocialMedia;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import lombok.extern.java.Log;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Log
@ApplicationScoped
public class PersonDB {
    private final Map<Integer,Person> DB = new ConcurrentHashMap<>();
    
    @Inject @ConfigProperty(name = "test.data.size")
    private int size;
    
    @Inject
    private Event<String> asyncStartupEvent;
    
    @Inject
    private Event<Person> personCreatedEvent;
    
    public Person getPerson(int id){
        log.log(Level.SEVERE, "======= Getting person [{0}] =======", id);
        return DB.get(id);
    }
    
    public List<Person> getPeople(){
        return new ArrayList<>(DB.values());
    }
    
    public List<Person> getPeopleWithSurname(String surname){
        log.log(Level.SEVERE, "======= Finding people with surname [{0}] =======", surname);
        List<Person> p = new ArrayList<>();
        for(Person person: DB.values()) {
            if(person.getSurname().equalsIgnoreCase(surname))p.add(person);
        }
        return p;
    }
    
    public Person updatePerson(Person person){
        if(person.getId() == null || person.getId() <= 0){
            person.setId(getNextId());
            log.log(Level.SEVERE, "======= Adding person [{0}] =======", person.getId());
        }else{
            log.log(Level.SEVERE, "======= Updating person [{0}] =======", person.getId());
        }
        DB.put(person.getId(), person);
        return person;
    }
    
    public Person deletePerson(int id){
        if(DB.containsKey(id)){
            log.log(Level.SEVERE, "======= Deleting person [{0}] =======", id);
            return DB.remove(id);
        }
        return null;
    }
    
    private synchronized int getNextId(){
        return DB.size() + 1;
    }
    
    // Generating test data
    private void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        asyncStartupEvent.fireAsync("Hello");
    }
    
    private void createTestData(@ObservesAsync String message) {
        // Create some dummy data
        for(int i=1;i<=size;i++){
            Person p = createRandomPerson(i); 
            personCreatedEvent.fire(p);
            DB.put(i, p);
        }
        log.info("==== All test data generated, ready to go ! ====");
        log.info("==== Go to http://localhost:8080 ====");
    }
    
    private Person createRandomPerson(int id){
        try {
            Faker faker = new Faker();
            
            Person person = new Person();
            person.setId(id);
            person.setLocale("en-ZA");
            person.setTitle(faker.name().prefix());
            person.addName(faker.name().firstName());
            person.addName(faker.name().firstName());
            person.addNickName(faker.funnyName().name());
            person.setSurname(faker.name().lastName());
            person.setUsername(faker.name().username());
            person.setFavColor(faker.color().name());
            person.setIdNumber(faker.idNumber().valid());
            person.addCoverPhoto(new URL(faker.internet().image()));
            person.addProfilePicture(new URL(faker.avatar().image()));
            
            person.setBirthDate(toLocalDate(faker.date().birthday()));
            
            Address work = new Address();
            work.setCode(faker.address().zipCode());
            work.setLines(new String[]{
                faker.address().buildingNumber() + " " + faker.address().streetName(),
                faker.address().cityName(),
                faker.address().state(),
                faker.address().country()
            });
            person.addAddress(work);
            
            Address street = new Address();
            street.setCode(faker.address().zipCode());
            street.setLines(new String[]{
                faker.address().buildingNumber() + " " + faker.address().streetName(),
                faker.address().cityName(),
                faker.address().state(),
                faker.address().country()
            });
            person.addAddress(street);
            
            person.addEmailAddress(faker.internet().emailAddress());
            person.addEmailAddress(faker.internet().emailAddress());

            person.addWebsite(new URL("http://" + faker.internet().url()));
            person.addWebsite(new URL("http://" + faker.internet().url()));

            CreditCard card = new CreditCard();
            card.setExpiry(faker.business().creditCardExpiry());
            card.setNumber(faker.business().creditCardNumber());
            card.setType(faker.business().creditCardType());
            person.addCreditCard(card);
            
            person.setOrganization(faker.company().name());
            person.setOccupation(faker.job().position());
            
            person.setJoinDate(toLocalDate(faker.date().past(10*365, TimeUnit.DAYS)));
            
            person.setGender(Gender.valueOf(faker.demographic().sex()));
            person.setMaritalStatus(faker.demographic().maritalStatus());

            person.addInterest(faker.esports().game());
            person.addInterest(faker.hipster().word());
            
            person.setUserAgent(faker.internet().userAgentAny());
            
            person.addSkill(faker.job().keySkills());
            person.addSkill(faker.job().keySkills());
            
            person.addPhoneNumber(new Phone("Cell", faker.phoneNumber().cellPhone()));
            person.addPhoneNumber(new Phone("Home", faker.phoneNumber().phoneNumber()));
            person.addPhoneNumber(new Phone("Work", faker.phoneNumber().phoneNumber()));
            
            person.addTagline(faker.yoda().quote());
            person.addTagline(faker.chuckNorris().fact());
            
            person.addImClient(new ImClient(faker.name().username(),"Slack"));
            person.addImClient(new ImClient(faker.name().username(),"ICQ"));

            person.addSocialMedia(new SocialMedia("@" + faker.name().username(),"Twitter"));
            person.addSocialMedia(new SocialMedia(faker.name().username(),"Facebook"));
            
            person.setBiography(faker.lorem().paragraph(6));

            person.addRelationship(new Relation(RelationType.Spouse, getRandonPersonURI(faker,id)));
            
            return person;
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private String getRandonPersonURI(Faker faker,int i){
        int rnd = faker.number().numberBetween(1, size);
        if(rnd !=i)return "/rest/person/" + rnd;
        return getRandonPersonURI(faker,i);        
    }
    
    private LocalDate toLocalDate(Date date){
        return date.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
    }
}
