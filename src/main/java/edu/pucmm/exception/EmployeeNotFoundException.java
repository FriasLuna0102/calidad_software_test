package edu.pucmm.exception;

/**
 * @author me@fredpena.dev
 * @created 02/06/2024  - 00:32
 */
public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
