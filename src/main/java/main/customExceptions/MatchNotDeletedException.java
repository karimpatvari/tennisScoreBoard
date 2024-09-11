package main.customExceptions;

public class MatchNotDeletedException extends Exception{
    public MatchNotDeletedException() {
        super();
    }

    public MatchNotDeletedException(String message) {
        super(message);
    }

    public MatchNotDeletedException(String message, Throwable cause) {
        super(message, cause);
    }
}
