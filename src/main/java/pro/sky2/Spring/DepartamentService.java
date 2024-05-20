package pro.sky2.Spring;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartamentService {
    private final EmployeeService employeeService;

    public DepartamentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee findEmployeeWithMaxSalaryFromDepartament(int departamentId) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartamentId() == departamentId)
                .max(Comparator.comparingInt(e -> e.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException());
    }
    public Employee findEmployeeWithMinSalaryFromDepartament(int departamentId) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartamentId() == departamentId)
                .min(Comparator.comparingInt(e -> e.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException());
    }
    public List<Employee> findEmployeesFromDepartament(int departamentId) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartamentId() == departamentId)
                .toList();
    }

    public Map<Integer, List<Employee>> employeesGroupByDepartament() {
        return employeeService.findAll().stream()
                .collect(Collectors.groupingBy(e -> e.getDepartamentId()));
    }

}
