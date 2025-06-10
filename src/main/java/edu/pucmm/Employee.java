package edu.pucmm;

import java.io.Serializable;

/**
 * @author me@fredpena.dev
 * @created 01/06/2024  - 23:37
 */
public class Employee implements Serializable {

    private String id;
    private String name;
    private Position position;
    private double salary;

    public Employee(String id, String name, Position position, double salary) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public Employee setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Employee setName(String name) {
        this.name = name;
        return this;
    }

    public Position getPosition() {
        return position;
    }

    public Employee setPosition(Position position) {
        this.position = position;
        return this;
    }

    public double getSalary() {
        return salary;
    }

    public Employee setSalary(double salary) {
        this.salary = salary;
        return this;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;

        return id.equals(employee.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
