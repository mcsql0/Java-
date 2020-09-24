package net.mcsql.controller;

import net.mcsql.bean.Employee;
import net.mcsql.servlet.EmployeeServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    public EmployeeServlet employeeServlet;

    @GetMapping("/emp/{id}")
    public String getEmployee(@PathVariable("id") Integer id) {
        if (id != null) {
            return employeeServlet.getEmployee(id).toString();
        }
        return "0";
    }

    @PostMapping("/emp")
    public String updataEmployee(Employee employee) {
        employeeServlet.updataEmployee(employee);
        return "1";
    }

    @DeleteMapping("/emp")
    public String delEmp(Integer id) {
        employeeServlet.delEmployee(id);
        return "sessact";
    }

}
