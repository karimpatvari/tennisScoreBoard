package main.customExceptions;

public class PlayerNotDeletedException extends Exception{
    public PlayerNotDeletedException() {
        super();
    }

    public PlayerNotDeletedException(String message) {
        super(message);
    }

    public PlayerNotDeletedException(String message, Throwable cause) {
        super(message, cause);
    }
}
