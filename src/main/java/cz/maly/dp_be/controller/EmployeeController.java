package cz.maly.dp_be.controller;

import cz.maly.dp_be.exception.ResourceNotFoundException;
import cz.maly.dp_be.model.Employee;
import cz.maly.dp_be.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @CrossOrigin
    @GetMapping("/employee")
    public List<Employee> getAllEmployees(HttpServletResponse response) {
        List<Employee> employees = employeeRepository.findAll();
        response.setHeader("Content-Range", String.valueOf(employees.size()));
        response.setHeader("Access-Control-Expose-Headers", "Content-Range");
        return employees;
    }

    @CrossOrigin
    @PostMapping("/employee")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @CrossOrigin
    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable(value = "id") Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
    }

    @CrossOrigin
    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable(value = "id") Long employeeId,
                                         @Valid @RequestBody Employee employeeDetails) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        // todo dopsat parametry
        //employee.setName(employeeDetails.getName());
        //employee.setEnvironment(employeeDetails.getEnvironment());

        Employee updatedEmployee = employeeRepository.save(employee);
        return updatedEmployee;
    }

    @CrossOrigin
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        employeeRepository.delete(employee);

        return ResponseEntity.ok().build();
    }

}
