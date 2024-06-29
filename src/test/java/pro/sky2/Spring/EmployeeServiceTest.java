package pro.sky2.Spring;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;
import pro.sky2.Spring.exeption.EmployeeAlreadyExistsException;
import pro.sky2.Spring.exeption.EmployeeNotFoundException;
import pro.sky2.Spring.exeption.EmployeeStorageIsFullException;
import pro.sky2.Spring.model.Employee;
import pro.sky2.Spring.service.EmployeeService;
import pro.sky2.Spring.service.ValidationService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class EmployeeServiceTest {
    private final EmployeeService employeeService = new EmployeeService(new ValidationService());
    private final List<Employee> employees = List.of(
            new Employee("Иван", "Иванов", 50000, 1),
            new Employee("Пётр", "Иванов", 60000, 1),
            new Employee("Алексей", "Иванов", 70000, 1)
    );

    @BeforeEach
    public void beforeEach() {
        employees.forEach((employee -> employeeService.add(employee.getFirstname(), employee.getLastName(), employee.getSalary(), employee.getDepartamentId())));
    }

    @AfterEach
    public void afterEach() {
        employeeService.findAll().forEach(employee -> employeeService.remove(employee.getFirstname(), employee.getLastName()));
    }

    @Test
    public void addTest() {
        Employee expected = new Employee("Андрей", "Иванов", 80000, 2);
        Employee actual = employeeService.add("Андрей", "Иванов", 80000, 2);
        assertThat(actual).isEqualTo(expected);
        assertThat(actual).isEqualTo(employeeService.find("Андрей", "Иванов"));
        assertThat(actual).isIn(employeeService.findAll());
    }

    @Test
    public void whenEmployeeServiceIsFullThenEmployeeStorageIsFullExceptionIsThrown() {
        employeeService.add("Андрей", "Иванов", 80000, 2);
        employeeService.add("Артем", "Иванов", 90000, 3);
        assertThatExceptionOfType(EmployeeStorageIsFullException.class)
                .isThrownBy(() -> employeeService.add("Александр", "Иванов", 100000, 3));
    }

    @Test
    public void when_employeeService_contains_employee_then_EmployeeAlreadyExistsExeption_is_thrown() {
        assertThatExceptionOfType(EmployeeAlreadyExistsException.class)
                .isThrownBy(() -> employeeService.add("Иван", "Иванов", 50000, 1));
    }

    @Test
    public void findTest() {
        Employee expected = new Employee("Иван", "Иванов", 50000, 1);
        assertThat(employeeService.findAll().contains(expected));
        Employee actual = employeeService.find("Иван", "Иванов");
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void findNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
        .isThrownBy(() ->employeeService.find("Андрей", "Иванов"));
    }
    @Test
    public void removeTest() {
        Employee expected = new Employee("Иван", "Иванов", 50000, 1);
        assertThat(employeeService.findAll().contains(expected));
        assertThat(employeeService.find("Иван", "Иванов")).isEqualTo(expected);
        Employee actual = employeeService.remove("Иван", "Иванов");
        assertThat(actual).isEqualTo(expected);
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("Иван", "Иванов"));
        assertThat(actual).isNotIn(employeeService.findAll());
    }

    @Test
    public void removeNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.remove("Андрей", "Иванов"));
    }

    @Test
    public void findAllTest() {
        assertThat(employeeService.findAll())
                .containsExactlyInAnyOrderElementsOf(employees);
    }
}
