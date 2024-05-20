package pro.sky2.Spring;

import java.util.Objects;
import java.util.Random;

public class Employee {
    private String firstname;
    private String lastName;
    private int salary;
    private int departamentId;

    public Employee(String firstname, String lastName, int salary, int departamentId) {
        this.firstname = firstname;
        this.lastName = lastName;
        this.salary = salary;
        this.departamentId = departamentId;
    }

    public Employee() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Employee employee = (Employee) o;
        return salary == employee.salary && departamentId == employee.departamentId && Objects.equals(firstname, employee.firstname) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastName, salary, departamentId);
    }

    @Override
    public String toString() {
        return "name = " + firstname + " surname = " + lastName + " salary = " + salary + " + departamentId = " + departamentId;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public int getSalary() {
        return salary;
    }

    public int getDepartamentId() {
        return departamentId;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setDepartamentId(int departamentId) {
        this.departamentId = departamentId;
    }
}