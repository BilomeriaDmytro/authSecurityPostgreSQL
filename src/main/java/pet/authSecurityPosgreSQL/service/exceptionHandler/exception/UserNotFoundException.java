package pet.authSecurityPosgreSQL.service.exceptionHandler.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String username) {
        super("User with username - '" + username + "' is not found.");
    }
}
