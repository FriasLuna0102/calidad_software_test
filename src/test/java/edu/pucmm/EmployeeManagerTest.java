package edu.pucmm;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.pucmm.exception.DuplicateEmployeeException;
import edu.pucmm.exception.EmployeeNotFoundException;
import edu.pucmm.exception.InvalidSalaryException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * @author me@fredpena.dev
 * @created 02/06/2024  - 00:47
 */ 

public class EmployeeManagerTest {

    private EmployeeManager employeeManager;
    private Position juniorDeveloper;
    private Position seniorDeveloper;
    private Employee employee1;
    private Employee employee2;
    

    @BeforeEach
    public void setUp() {
        employeeManager = new EmployeeManager();
        juniorDeveloper = new Position("1", "Junior Developer", 30000, 50000);
        seniorDeveloper = new Position("2", "Senior Developer", 60000, 90000);
        employee1 = new Employee("1", "John Doe", juniorDeveloper, 40000);
        employee2 = new Employee("2", "Jane Smith", seniorDeveloper, 70000);
        employeeManager.addEmployee(employee1);
    }

    @Test
    public void testAddEmployee() {
        // TODO: Agregar employee2 al employeeManager y verificar que se agregó correctamente.
        // - Verificar que el número total de empleados ahora es 2.
        // - Verificar que employee2 está en la lista de empleados.
        employeeManager.addEmployee(employee2);
        assertEquals(2, employeeManager.getEmployees().size());
        assertTrue(employeeManager.getEmployees().contains(employee2));
        assertTrue(true);
    }

    @Test
    public void testRemoveEmployee() {
        // TODO: Eliminar employee1 del employeeManager y verificar que se eliminó correctamente.
        // - Agregar employee2 al employeeManager.
        // - Eliminar employee1 del employeeManager.
        // - Verificar que el número total de empleados ahora es 1.
        // - Verificar que employee1 ya no está en la lista de empleados.
        employeeManager.addEmployee(employee2);
        employeeManager.removeEmployee(employee1);
        assertEquals(1, employeeManager.getEmployees().size());
        assertFalse(employeeManager.getEmployees().contains(employee1));
        assertTrue(employeeManager.getEmployees().contains(employee2));
    }

    @Test
    public void testCalculateTotalSalary() {
        // TODO: Agregar employee2 al employeeManager y verificar el cálculo del salario total.
        // - Agregar employee2 al employeeManager.
        // - Verificar que el salario total es la suma de los salarios de employee1 y employee2.
        employeeManager.addEmployee(employee2);
        assertEquals(40000 + 70000, employeeManager.calculateTotalSalary());
        assertTrue(true);
    }

    @Test
    public void testUpdateEmployeeSalaryValid() {
        // TODO: Actualizar el salario de employee1 a una cantidad válida y verificar la actualización.
        // - Actualizar el salario de employee1 a 45000.
        // - Verificar que el salario de employee1 ahora es 45000.
        employeeManager.updateEmployeeSalary(employee1, 45000);
        assertEquals(45000, employee1.getSalary());
        assertTrue(true);
    }   

    @Test
    public void testUpdateEmployeeSalaryInvalid() {
        // TODO: Intentar actualizar el salario de employee1 a una cantidad inválida y verificar la excepción.
        // - Intentar actualizar el salario de employee1 a 60000 (que está fuera del rango para Junior Developer).
        // - Verificar que se lanza una InvalidSalaryException.
        assertThrows(InvalidSalaryException.class, () -> {
            employeeManager.updateEmployeeSalary(employee1, 60000);
        });
    }

    @Test
    public void testUpdateEmployeeSalaryEmployeeNotFound() {
        // TODO: Intentar actualizar el salario de employee2 (no agregado al manager) y verificar la excepción.
        // - Intentar actualizar el salario de employee2 a 70000.
        // - Verificar que se lanza una EmployeeNotFoundException.
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeManager.updateEmployeeSalary(employee2, 70000);
        });
    }

    @Test
    public void testUpdateEmployeePositionValid() {
        // TODO: Actualizar la posición de employee2 a una posición válida y verificar la actualización.
        // - Agregar employee2 al employeeManager.
        // - Actualizar la posición de employee2 a seniorDeveloper.
        // - Verificar que la posición de employee2 ahora es seniorDeveloper.
        employeeManager.addEmployee(employee2);
        employeeManager.updateEmployeePosition(employee2, seniorDeveloper);
        assertEquals(seniorDeveloper, employee2.getPosition());
        assertTrue(true);
    }

    @Test
    public void testUpdateEmployeePositionInvalidDueToSalary() {
        // TODO: Intentar actualizar la posición de employee1 a seniorDeveloper y verificar la excepción.
        // - Intentar actualizar la posición de employee1 a seniorDeveloper.
        // - Verificar que se lanza una InvalidSalaryException porque el salario de employee1 no está dentro del rango para Senior Developer.
        assertThrows(InvalidSalaryException.class, () -> {
            employeeManager.updateEmployeePosition(employee1, seniorDeveloper);
        });
    }

    @Test
    public void testUpdateEmployeePositionEmployeeNotFound() {
        // TODO: Intentar actualizar la posición de employee2 (no agregado al manager) y verificar la excepción.
        // - Intentar actualizar la posición de employee2 a juniorDeveloper.
        // - Verificar que se lanza una EmployeeNotFoundException.
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeManager.updateEmployeePosition(employee2, juniorDeveloper);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "Junior Developer, 30000, 50000, 35000, true",  // Salario válido para Junior
            "Junior Developer, 30000, 50000, 55000, false", // Salario por encima del rango Junior
            "Junior Developer, 30000, 50000, 25000, false", // Salario por debajo del rango Junior
            "Senior Developer, 60000, 90000, 75000, true",  // Salario válido para Senior
            "Senior Developer, 60000, 90000, 55000, false", // Salario por debajo del rango Senior
            "Senior Developer, 60000, 90000, 95000, false"  // Salario por encima del rango Senior
    })
    void testSalaryValidationForPositions(String positionName, double minSalary,
                                          double maxSalary, double salaryToTest,
                                          boolean expectedResult) {
        Position position = new Position("TEST", positionName, minSalary, maxSalary);
        assertEquals(expectedResult, employeeManager.isSalaryValidForPosition(position, salaryToTest));
    }

    private static Stream<Arguments> provideSalaryTestCases() {
        return Stream.of(
                arguments(new Position("J1", "Junior Developer", 30000, 50000), 35000, true),
                arguments(new Position("J2", "Junior Developer", 30000, 50000), 55000, false),
                arguments(new Position("S1", "Senior Developer", 60000, 90000), 75000, true),
                arguments(new Position("S2", "Senior Developer", 60000, 90000), 55000, false),
                arguments(new Position("TL", "Team Lead", 80000, 120000), 100000, true),
                arguments(new Position("TL", "Team Lead", 80000, 120000), 70000, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideSalaryTestCases")
    void testSalaryValidationUsingMethodSource(Position position, double salary, boolean expectedResult) {
        assertEquals(expectedResult, employeeManager.isSalaryValidForPosition(position, salary));
    }
    
    @Test
    public void testAddEmployeeWithInvalidSalary() {
        // TODO: Intentar agregar empleados con salarios inválidos y verificar las excepciones.
        // - Crear un empleado con un salario de 60000 para juniorDeveloper.
        // - Verificar que se lanza una InvalidSalaryException al agregar este empleado.
        // - Crear otro empleado con un salario de 40000 para seniorDeveloper.
        // - Verificar que se lanza una InvalidSalaryException al agregar este empleado.    
        assertThrows(InvalidSalaryException.class, () -> {
            employeeManager.addEmployee(new Employee("3", "John Doe", juniorDeveloper, 60000));
        });
        assertThrows(InvalidSalaryException.class, () -> {
            employeeManager.addEmployee(new Employee("4", "Jane Smith", seniorDeveloper, 40000));
        });
    }

    @Test
    public void testRemoveExistentEmployee() {
        // TODO: Eliminar un empleado existente y verificar que no se lanza una excepción.
        // - Eliminar employee1 del employeeManager.
        // - Verificar que no se lanza ninguna excepción.
        employeeManager.removeEmployee(employee1);
        assertEquals(0, employeeManager.getEmployees().size());
        assertFalse(employeeManager.getEmployees().contains(employee1));
    }

    @Test
    public void testRemoveNonExistentEmployee() {
        // TODO: Intentar eliminar un empleado no existente y verificar la excepción.
        // - Intentar eliminar employee2 (no agregado al manager).
        // - Verificar que se lanza una EmployeeNotFoundException.
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeManager.removeEmployee(employee2);
        });
    }

    @Test
    public void testAddDuplicateEmployee() {
        // TODO: Intentar agregar un empleado duplicado y verificar la excepción.
        // - Intentar agregar employee1 nuevamente al employeeManager.
        // - Verificar que se lanza una DuplicateEmployeeException.
        assertThrows(DuplicateEmployeeException.class, () -> {
            employeeManager.addEmployee(employee1);
        });
    }

    @Test
    public void testEmployeeSetters() {
        Employee employee = new Employee("1", "John Doe", juniorDeveloper, 40000);

        employee.setId("100")
                .setName("Jane Doe")
                .setPosition(seniorDeveloper)
                .setSalary(80000);

        assertEquals("100", employee.getId());
        assertEquals("Jane Doe", employee.getName());
        assertEquals(seniorDeveloper, employee.getPosition());
        assertEquals(80000, employee.getSalary());
    }

    @Test
    public void testEmployeeEqualsAndHashCode() {
        Employee employee1Copy = new Employee("1", "Different Name", seniorDeveloper, 75000);
        Employee differentEmployee = new Employee("2", "John Doe", juniorDeveloper, 40000);

        assertEquals(employee1, employee1);
        assertEquals(employee1, employee1Copy);
        assertNotEquals(employee1, differentEmployee);
        assertNotEquals(null, employee1);
        assertNotEquals(new Object(), employee1);

        assertEquals(employee1.hashCode(), employee1Copy.hashCode());
        assertNotEquals(employee1.hashCode(), differentEmployee.hashCode());
    }

    @Test
    public void testPositionSetters() {
        Position position = new Position("1", "Test Position", 30000, 50000);

        position.setId("100")
                .setName("New Position")
                .setMinSalary(40000)
                .setMaxSalary(60000);

        assertEquals("100", position.getId());
        assertEquals("New Position", position.getName());
        assertEquals(40000, position.getMinSalary());
        assertEquals(60000, position.getMaxSalary());
    }

    @Test
    public void testPositionEqualsAndHashCode() {
        Position position1Copy = new Position("1", "Different Name", 35000, 55000);
        Position differentPosition = new Position("2", "Test Position", 30000, 50000);

        assertEquals(juniorDeveloper, juniorDeveloper);
        assertEquals(juniorDeveloper, position1Copy);
        assertNotEquals(juniorDeveloper, differentPosition);
        assertNotEquals(null, juniorDeveloper);
        assertNotEquals(new Object(), juniorDeveloper);

        assertEquals(juniorDeveloper.hashCode(), position1Copy.hashCode());
        assertNotEquals(juniorDeveloper.hashCode(), differentPosition.hashCode());
    }


}
