package mcsql.net.controller;

import mcsql.net.dao.DepartmentDao;
import mcsql.net.dao.EmployeeDao;
import mcsql.net.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class empsController {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    @GetMapping("emps")
    public ModelAndView getEmps() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("emps", employeeDao.getAll());
        modelAndView.setViewName("list");
        return modelAndView;
    }

    @DeleteMapping("emp/{id}")
    public String delEmp(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }

    /**
     * 员工添加页面.
     * @return
     */
    @GetMapping("emp")
    public String addEmp(Map<String,Object> map) {
        map.put("depts", departmentDao.getDepartments());
        return "add";
    }

    /**
     * 员工添加请求处理.
     * @return
     */
    @PostMapping("emp")
    public String postEmp(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/emps";
    }

}
