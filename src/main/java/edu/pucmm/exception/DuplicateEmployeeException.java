package edu.pucmm.exception;

/**
 * @author me@fredpena.dev
 * @created 02/06/2024  - 00:31
 */
public class DuplicateEmployeeException extends RuntimeException {
    public DuplicateEmployeeException(String message) {
        super(message);
    }
}
