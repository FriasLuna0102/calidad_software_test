package edu.pucmm.exception;

/**
 * @author me@fredpena.dev
 * @created 02/06/2024  - 00:31
 */
public class InvalidSalaryException extends RuntimeException {
    public InvalidSalaryException(String message) {
        super(message);
    }
}
