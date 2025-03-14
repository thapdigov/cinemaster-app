package az.turing.cinemamasterapp.exception;

public class InvalidPasswordConfirmationException extends RuntimeException {
    public InvalidPasswordConfirmationException(String message) {
        super(message);
    }
}
