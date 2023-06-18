package vsu.julia.holidaycalendarapp.exceptions;

public class BadParamsException extends Exception {
    public BadParamsException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadParamsException(String message) {
        super(message);
    }
}
