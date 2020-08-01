package com.github.phillipkruger.user.graphql;

public class ScoresNotAvailableException extends Exception {

    public ScoresNotAvailableException() {
    }

    public ScoresNotAvailableException(String string) {
        super(string);
    }

    public ScoresNotAvailableException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public ScoresNotAvailableException(Throwable thrwbl) {
        super(thrwbl);
    }

    public ScoresNotAvailableException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }
}