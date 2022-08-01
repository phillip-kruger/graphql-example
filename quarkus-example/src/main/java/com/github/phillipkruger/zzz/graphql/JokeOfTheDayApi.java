package com.github.phillipkruger.zzz.graphql;

import java.util.List;
import java.util.Random;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
public class JokeOfTheDayApi {

    Random r = new Random();
    
    @Query
    public Joke randomJoke(){
        return jokes.get(r.nextInt(jokes.size()));  
    }
    
    
    private static List<Joke> jokes = List.of(Joke.questionAndAnswer("What do you call a fish without eyes?","Fsh"),
            Joke.questionAndAnswer("What do you call an alligator detective?","An investi-gator"),
            Joke.questionAndAnswer("Why did the scarecrow win an award?","Because he was outstanding in his field"),
            Joke.questionAndAnswer("Why shouldn’t you write with a broken pencil?","Because it’s pointless"),
            Joke.questionAndAnswer("What did the policeman say to his bellybutton?","You’re under a vest"),
            Joke.questionAndAnswer("What do you call a fake noodle?","An impasta!"),
            Joke.questionAndAnswer("What do you call something that runs but never gets anywhere?","A refrigerator."),
            Joke.questionAndAnswer("What do you call something that’s easy to get into, but hard to get out of?","Trouble."),
            Joke.questionAndAnswer("What do you do to get a robot mad?","Push all of its buttons."),
            Joke.questionAndAnswer("What do you call a joke without a punchline?","Silence."),
            Joke.questionAndAnswer("What do you call a horse that can’t lose a race?","A sherbet."),
            Joke.questionAndAnswer("What do you call the security guards outside the Samsung factory?","The Guardians of the Galaxy."),
            Joke.questionAndAnswer("What do you call someone that saw an iPhone being stolen?","An iWitness."),
            Joke.questionAndAnswer("What do you call someone who never passes gas in public?","A private tutor."),
            Joke.questionAndAnswer("What do you call it when a prisoner takes his own mugshot?","A cellfie."),
            Joke.questionAndAnswer("What do you call blackbirds that stick together?","Vel-crows."),
            Joke.questionAndAnswer("What do you call the daughter of a hamburger?","Patty."),
            Joke.questionAndAnswer("What do you call a tiny mother?","A minimum!"),
            Joke.questionAndAnswer("What do you call a policeman in bed?","An undercover cop."),
            Joke.questionAndAnswer("What do you call a soldier who survived mustard gas and pepper spray?","A seasoned veteran."),
            Joke.questionAndAnswer("What do you call a funny mountain?","Hill-arious."),
            Joke.questionAndAnswer("What do you call a boomerang that doesn’t come back?","A stick."),
            Joke.questionAndAnswer("What do you call a factory that manufactures products that are just OK?","A satisfactory."),
            Joke.questionAndAnswer("What do you call a bagel that can fly?","A plain bagel."),
            Joke.questionAndAnswer("What do you call a person with a briefcase in a tree?","Branch manager."),
            Joke.questionAndAnswer("What do you call someone who cleans the bottom of the ocean?","A mer-maid."),
            Joke.questionAndAnswer("What do you call something that goes up when the rain comes down?","An umbrella."),
            Joke.questionAndAnswer("What do you call a doctor who fixes websites?","A URL-ologist.")
    );
}
