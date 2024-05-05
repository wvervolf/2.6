package pro.sky2.Spring;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    private final int MaxEmploees = 10;
    private final Map<String, Employee> employees = new HashMap();

    public Employee add(String firstName, String lastName) {
        String key = buildkey(firstName, lastName);
        if(employees.containsKey(key)){
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() > MaxEmploees) {
            throw new EmployeeStorageIsFullException();
        }
        Employee employee = new Employee(firstName, lastName);
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
