package main.customExceptions;

public class PlayerNotCreatedException extends Exception {
    public PlayerNotCreatedException() {
        super();
    }

    public PlayerNotCreatedException(String message) {
        super(message);
    }

    public PlayerNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
