package com.github.phillipkruger.zzz.graphql;


public class Joke {
    public String question;
    public String answer;
    
    public static Joke questionAndAnswer(String q, String a){
        Joke j = new Joke();
        j.question = q;
        j.answer = a;
        return j;
    }
}
