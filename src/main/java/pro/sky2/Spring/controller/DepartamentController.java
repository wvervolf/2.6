package pro.sky2.Spring.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky2.Spring.model.Employee;
import pro.sky2.Spring.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departament")
public class DepartamentController {
    private final DepartmentService departmentService;
    public DepartamentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/employees")
    public List<Employee> findEmployeesFromDepartment(@PathVariable("id") int departamentId){
        return departmentService.findEmployeesFromDepartment(departamentId);
    }
    @GetMapping("/{id}/salary/sum")
    public Integer calculateSumOfSalariesOfEmploeesFromDepartment(@PathVariable("id") int departamentId){
        return departmentService.calculateSumOfSalariesOfEmploeesFromDepartment(departamentId);
    }
    @GetMapping("/{id}/salary/max")
    public Employee findMaxSalaryFromDepartament(@PathVariable("id") int departamentId){
        return departmentService.findMaxSalaryFromDepartment(departamentId);

    }
    @GetMapping("/{id}/salary/min")
    public Employee findMinSalaryFromDepartament(@PathVariable("id") int departamentId){
        return departmentService.findMinSalaryFromDepartment(departamentId);
    }

    @GetMapping(value = "/all")
    public Map<Integer, List<Employee>> findEmployeesGroupedByDepartament(){
        return departmentService.findEmployeesGroupedByDepartment();
    }
}
