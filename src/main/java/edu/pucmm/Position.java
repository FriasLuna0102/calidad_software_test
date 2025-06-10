package edu.pucmm;

import java.io.Serializable;

/**
 * @author me@fredpena.dev
 * @created 01/06/2024  - 23:38
 */
public class Position implements Serializable {

    private String id;
    private String name;
    private double minSalary;
    private double maxSalary;

    public Position(String id, String name, double minSalary, double maxSalary) {
        this.id = id;
        this.name = name;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }

    public String getId() {
        return id;
    }

    public Position setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Position setName(String name) {
        this.name = name;
        return this;
    }

    public double getMinSalary() {
        return minSalary;
    }

    public Position setMinSalary(double minSalary) {
        this.minSalary = minSalary;
        return this;
    }

    public double getMaxSalary() {
        return maxSalary;
    }

    public Position setMaxSalary(double maxSalary) {
        this.maxSalary = maxSalary;
        return this;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position position)) return false;

        return id.equals(position.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
