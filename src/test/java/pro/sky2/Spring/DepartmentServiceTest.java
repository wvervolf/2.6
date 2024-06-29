package pro.sky2.Spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky2.Spring.exeption.EmployeeNotFoundException;
import pro.sky2.Spring.model.Employee;
import pro.sky2.Spring.service.DepartmentService;
import pro.sky2.Spring.service.EmployeeService;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private DepartmentService departmentService;
    private final List<Employee> employees = List.of(
            new Employee("Иван", "Иванов", 50000, 1),
            new Employee("Петр", "Иванов", 50000, 1),
            new Employee("Алексей", "Иванов", 50000, 1),
            new Employee("Андрей", "Иванов", 50000, 1),
            new Employee("Александр", "Иванов", 50000, 1)
    );

    @BeforeEach
    public void BeforeEach() {
        when(employeeService.findAll()).thenReturn(employees);
    }
    @Test
    public void findEmployeesFromDepartmentTest() {
        assertThat(departmentService.findEmployeesFromDepartment(1))
                .containsExactlyInAnyOrder(
                        new Employee("Иван", "Иванов", 50000, 1),
                        new Employee("Пётр", "Иванов", 60000, 1)
                );
    }
    @Test
    public void calculateSumOfSalariesOfEmploeesFromDepartmentTest() {
        assertThat(departmentService.calculateSumOfSalariesOfEmploeesFromDepartment(2))
                .isEqualTo(150000);
    }
    @Test
    public void calculateSumOfSalariesOfEmploeesFromDepartmentNegativeTest() {
        assertThat(departmentService.calculateSumOfSalariesOfEmploeesFromDepartment(4))
                .isEqualTo(0);
    }
    @Test
    public void findMaxSalaryFromDepartmentTest() {
        assertThat(departmentService.findMaxSalaryFromDepartment(2))
                .isEqualTo(80000);
    }
    @Test
    public void findMaxSalaryFromDepartmentNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departmentService.findMinSalaryFromDepartment(4));
    }
    @Test
    public void findEmployeesGroupedByDepartmentTest() {
        assertThat(departmentService.findEmployeesGroupedByDepartment())
                .containsExactlyInAnyOrderEntriesOf(
                        Map.of(
                                1, List.of(new Employee("Иван", "Иванов", 50000, 1), new Employee("Пётр", "Иванов", 60000, 2)),
                                2, List.of(new Employee("Андрей", "Иванов", 70000, 3), new Employee("Семен", "Иванов", 60000, 2)),
                                2, List.of(new Employee("Олег", "Иванов", 90000, 3))
                        ));
    }

}
