# Primer Parcial - ICC-443 - Aseguramiento Calidad Software

## _Pruebas Unitarias con JUnit_

### Descripción

Este proyecto tiene como objetivo evaluar tus competencias técnicas en pruebas unitarias utilizando JUnit. Además de
probar comportamientos funcionales, deberás validar condiciones de borde, reglas de negocio adicionales y excepciones.

Se te proporcionan las clases `EmployeeManager`, `Employee`, y `Position`, junto con una clase base de pruebas
`EmployeeManagerTest` con métodos de prueba parcialmente definidos mediante comentarios.

### Clases Proporcionadas

#### `Position`

Representa una posición dentro de la empresa.

```java
public class Position implements Serializable {
    private String id;
    private String name;
    private double minSalary;
    private double maxSalary;
}
```

#### `Employee`

Representa un empleado de la empresa.

```java
public class Employee implements Serializable {
    private String id;
    private String name;
    private Position position;
    private double salary;
}
```

#### `EmployeeManager`

Gestiona las operaciones relacionadas con los empleados.

```java
public class EmployeeManager {
    public void addEmployee(Employee employee) { /* Implementación */ }

    public void removeEmployee(Employee employee) { /* Implementación */ }

    public double calculateTotalSalary() { /* Implementación */ }

    public void updateEmployeeSalary(Employee employee, double newSalary) { /* Implementación */ }

    public void updateEmployeePosition(Employee employee, Position newPosition) { /* Implementación */ }

    public boolean isSalaryValidForPosition(Position position, double salary) { /* Implementación */ }

    public List<Employee> getEmployees() { /* Implementación */ }
}

```

### Excepciones Personalizadas

Se han proporcionado las siguientes excepciones personalizadas:

* `DuplicateEmployeeException`
* `EmployeeNotFoundException`
* `InvalidSalaryException`

### Reglas de Negocio

- No puede haber dos empleados con el mismo ID o nombre.
- El método isSalaryValidForPosition(...) debe retornar false si:
    - El salario es negativo.
    - El Position es null.
- No se permite agregar empleados con salarios por debajo del 10% del salario mínimo de su posición.
- Si se actualiza el salario y queda fuera del rango permitido para su posición, se debe lanzar InvalidSalaryException.
- Si se intenta actualizar el salario de un empleado que no existe, se debe lanzar EmployeeNotFoundException.
- Al cambiar la posición de un empleado:
    - Si el nuevo salario no es válido para la nueva posición, se debe lanzar InvalidSalaryException.
    - Si el salario está fuera de rango pero dentro del 10% inferior del mínimo permitido, se debe ajustar
      automáticamente al
      salario mínimo de la nueva posición.
- No se puede eliminar un empleado que no esté registrado en el sistema. Debe lanzarse EmployeeNotFoundException.
- El método calculateTotalSalary() debe reflejar correctamente el total, sin incluir empleados eliminados.
- El sistema debe permitir validar múltiples rangos de salario con pruebas parametrizadas.
- La comparación de empleados (por igualdad) debe basarse en el ID.

### Tarea

Debes completar los métodos de prueba en `EmployeeManagerTest` de acuerdo con los comentarios proporcionados y las
reglas de negocio.

Cada método de prueba incluye comentarios que describen lo que se debe probar. Los métodos estan disponible en la
clase `EmployeeManagerTest`

### Requisitos de Cobertura de Pruebas

Para obtener el 100% de la evaluación, la cobertura de pruebas debe estar al 100% en la clase `EmployeeManager` en las
siguientes áreas:

Clase (Class)
Método (Method)
Línea (Line)
Rama (Branch)

Tal como se muestra en la imagen a continuación

<img width="968" alt="image" src="https://github.com/fredpena/employee-management/assets/5680906/5449dc06-0ef7-4a1f-836a-9b0b0fb3f4ed">


Asegúrate de que todas las rutas del código sean cubiertas por tus pruebas unitarias.

### Criterios de Evaluación

- Cobertura 100% (clase, método, línea, rama) => 40%
- Calidad y corrección de pruebas unitarias => 30%
- Manejo de excepciones y reglas complejas => 20%
- Organización y buenas prácticas en el test => 10%

### BONUS

Si implementas al menos un @ParameterizedTest correctamente para validar distintos rangos salariales y posiciones,
recibirás puntos adicionales.

### Instrucciones

1. Clona este repositorio en tu máquina local.
2. Importa el proyecto en tu IDE favorito.
3. Navega a la clase `EmployeeManagerTest`.
4. Completa los métodos de prueba según los comentarios proporcionados.
5. Ejecuta las pruebas para asegurarte de que todo funciona correctamente.
6. Utiliza una herramienta de cobertura de código para verificar que has alcanzado el 100% de cobertura en
   `EmployeeManager`

### ¡Buena suerte y feliz codificación!

Este archivo README proporciona toda la información necesaria para que los estudiantes comprendan el objetivo del
parcial, las clases involucradas, las tareas a realizar, y los requisitos de cobertura de pruebas para alcanzar el 100%
de la evaluación.
