package pro.sky2.Spring;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Service
public class EmployeeService {
    private final int MaxEmploees = 10;
    private final List<Employee> employees = new ArrayList<>();

    public Employee add(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if(employees.contains(employee)){
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() > MaxEmploees) {
            throw new EmployeeStorageIsFullException();
        }
        employees.add(employee);
        return employee;
    }


    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if(!employees.contains(employee)){
            throw new EmployeeNotFoundException();
        }
        employees.remove(employee);
        return employee;
    }


    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if(!employees.contains(employee)){
            throw new EmployeeNotFoundException();
        }
        return employee;
    }
    public List<Employee> findAll() {
        return Collections.unmodifiableList(employees);
    }

}
