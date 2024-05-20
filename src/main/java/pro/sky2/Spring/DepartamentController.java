package pro.sky2.Spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departaments")
public class DepartamentController {
    private final DepartamentService departamentService;
    public DepartamentController(DepartamentService departamentService) {
        this.departamentService = departamentService;
    }

    @GetMapping("/max-salary")
    public Employee findEmployeeWithMaxSalaryFromDepartament(@RequestParam int departamentId){
        return departamentService.findEmployeeWithMaxSalaryFromDepartament(departamentId);

    }
    @GetMapping("/min-salary")
    public Employee findEmployeeWithMinSalaryFromDepartament(@RequestParam int departamentId){
        return departamentService.findEmployeeWithMinSalaryFromDepartament(departamentId);
    }
    @GetMapping(value = "/all", params = "/departamentId")
    public List<Employee> findEmployeeFromDepartament(@RequestParam int departamentId){
        return departamentService.findEmployeesFromDepartament(departamentId);
    }
    @GetMapping(value = "/all")
    public Map<Integer, List<Employee>> EmployeesGroupedByDepartament(){
        return departamentService.employeesGroupByDepartament();
    }
}
