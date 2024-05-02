package pro.sky2.Spring;

import java.util.Objects;

public class Employee {
    private String firstname;
    private String lastName;
    public Employee(String firstName, String lastName) {
        this.firstname = firstName;
        this.lastName = lastName;

    }

    public Employee() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return Objects.equals(firstname, employee.firstname) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastName);
    }

    @Override
    public String toString() {
        return "name = " + firstname + " surname = " + lastName;
    }

    public String getName() {
        return firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setName(String name) {
        this.firstname = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}