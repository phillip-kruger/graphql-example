package com.github.phillipkruger.user;

import java.net.URL;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import javax.json.bind.annotation.JsonbDateFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private Integer id;
    private String locale;
    private String title;
    private List<String> names;
    private List<String> nicknames;
    private String surname;
    private String username;
    private String idNumber;
    private List<URL> coverphotos;
    private List<URL> profilePictures;
    
    private Gender gender;
    @JsonbDateFormat("dd/MM/yyyy")
    private LocalDate birthDate;
    private String favColor;
    private List<Address> addresses;
    private List<String> emailAddresses;
    private List<Phone> phoneNumbers;
    private List<ImClient> imClients;
    private List<SocialMedia> socialMedias;
    private URL website;
    private List<String> taglines;
    private String biography;
    private String organization;
    private String occupation;
    private List<String> interests;
    private List<String> skills;
    
    private List<Relation> relations;
    @JsonbDateFormat("dd/MM/yyyy")
    private LocalDate joinDate;
    private String maritalStatus;
    
    private List<CreditCard> creditCards;
    
    private String userAgent;
    
    public void addName(String name){
        if(names==null)names = new LinkedList<>();
        names.add(name);
    }
    
    public void addNickName(String nickname){
        if(nicknames==null)nicknames = new LinkedList<>();
        nicknames.add(nickname);
    }
    
    public void addCoverPhoto(URL coverphoto){
        if(coverphotos==null)coverphotos = new LinkedList<>();
        coverphotos.add(coverphoto);
    }
    
    public void addProfilePicture(URL profilePicture){
        if(profilePictures==null)profilePictures = new LinkedList<>();
        profilePictures.add(profilePicture);
    }
    
    public void addAddress(Address address){
        if(addresses==null)addresses = new LinkedList<>();
        addresses.add(address);
    }
    
    public void addEmailAddress(String emailAddress){
        if(emailAddresses==null)emailAddresses = new LinkedList<>();
        emailAddresses.add(emailAddress);
    }
            
    public void addPhoneNumber(Phone phoneNumber){
        if(phoneNumbers==null)phoneNumbers = new LinkedList<>();
        phoneNumbers.add(phoneNumber);
    }

    public void addImClient(ImClient imClient){
        if(imClients==null)imClients = new LinkedList<>();
        imClients.add(imClient);
    }
    
    public void addSocialMedia(SocialMedia socialMedia){
        if(socialMedias==null)socialMedias = new LinkedList<>();
        socialMedias.add(socialMedia);
    }
    
    public void addTagline(String tagline){
        if(taglines==null)taglines = new LinkedList<>();
        taglines.add(tagline);
    }
    
    public void addInterest(String interest){
        if(interests==null)interests = new LinkedList<>();
        interests.add(interest);
    }
    
    public void addSkill(String skill){
        if(skills==null)skills = new LinkedList<>();
        skills.add(skill);
    }
    
    public void addRelationship(Relation relation){
        if(relations==null)relations = new LinkedList<>();
        relations.add(relation);
    }
    
    public void addCreditCard(CreditCard card){
        if(creditCards==null)creditCards = new LinkedList<>();
        creditCards.add(card);
    }
}