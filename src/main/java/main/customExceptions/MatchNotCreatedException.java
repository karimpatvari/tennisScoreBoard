package main.customExceptions;

public class MatchNotCreatedException extends Exception{
    public MatchNotCreatedException() {
        super();
    }

    public MatchNotCreatedException(String message) {
        super(message);
    }

    public MatchNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
