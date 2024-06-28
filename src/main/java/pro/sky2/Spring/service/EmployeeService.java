package pro.sky2.Spring.service;

import org.springframework.stereotype.Service;
import pro.sky2.Spring.exeption.EmployeeAlreadyExistsException;
import pro.sky2.Spring.exeption.EmployeeNotFoundException;
import pro.sky2.Spring.exeption.EmployeeStorageIsFullException;
import pro.sky2.Spring.model.Employee;

import java.util.*;

@Service
public class EmployeeService {
    private final int maxEmploees = 5;
    private final Map<String, Employee> employees = new HashMap();
    private final ValidationService validationService;
    public EmployeeService(ValidationService validationService) {
        this.validationService = validationService;
    }

    public Employee add(String firstName,
                        String lastName,
                        int salary,
                        int departamentId) {
        firstName = validationService.validateCheckName(firstName);
        lastName = validationService.validateCheckName(lastName);
        String key = buildkey(firstName, lastName);
        if(employees.containsKey(key)){
            throw new EmployeeAlreadyExistsException();
        }
        if (employees.size() > maxEmploees) {
            throw new EmployeeStorageIsFullException();
        }
        Employee employee = new Employee(firstName, lastName, salary, departamentId);
        employees.put(key, employee);
        return employee;
    }


    public Employee remove(String firstName, String lastName) {
        String key = buildkey(firstName, lastName);
        if(!employees.containsKey(key)){
            throw new EmployeeNotFoundException();
        }
        return employees.remove(key);
    }


    public Employee find(String firstName, String lastName) {
        String key = buildkey(firstName, lastName);
        if(!employees.containsKey(key)){
            throw new EmployeeNotFoundException();
        }
        return employees.get(key);
    }

    private String buildkey(String firstName, String lastName) {
        return firstName + lastName;
    }
    public List<Employee> findAll() {
        return List.copyOf(employees.values());
    }

}
