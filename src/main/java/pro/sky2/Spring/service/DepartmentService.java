package pro.sky2.Spring.service;

import org.springframework.stereotype.Service;
import pro.sky2.Spring.exeption.EmployeeNotFoundException;
import pro.sky2.Spring.model.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee findMinSalaryFromDepartment(int departamentId) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartamentId() == departamentId)
                .max(Comparator.comparingInt(e -> e.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException());
    }

    public Employee findMaxSalaryFromDepartment(int departamentId) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartamentId() == departamentId)
                .min(Comparator.comparingInt(e -> e.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException());
    }
    public List<Employee> findEmployeesFromDepartment(int departamentId) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartamentId() == departamentId)
                .toList();
    }

    public Map<Integer, List<Employee>> findEmployeesGroupedByDepartment() {
        return employeeService.findAll().stream()
                .collect(Collectors.groupingBy(e -> e.getDepartamentId()));
    }

    public Integer calculateSumOfSalariesOfEmploeesFromDepartment(int departamentId) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartamentId() == departamentId)
                .map(Employee::getSalary)
                .reduce(0, Integer::sum);
    }
}
