package main.customExceptions;

public class MatchNotFoundException extends Exception{
    public MatchNotFoundException() {
        super();
    }

    public MatchNotFoundException(String message) {
        super(message);
    }

    public MatchNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
