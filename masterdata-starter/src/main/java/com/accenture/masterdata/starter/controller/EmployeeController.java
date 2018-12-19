package com.accenture.masterdata.starter.controller;

import com.accenture.masterdata.core.entity.Employee;
import com.accenture.masterdata.service.EmployeeService;
import com.accenture.smsf.framework.starter.web.core.annotation.RestController;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author s.c.gao
 */
@RestController
@RequestMapping("/masterdata/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/save")
    public int employeeSave(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @PostMapping("/batch-save")
    public int employeeBatchSave(@RequestBody List<Employee> employees) {
        return employeeService.save(employees);
    }

    @DeleteMapping("/delete")
    public int employeeDelete(@RequestParam("id") String id) {
	    return employeeService.delete(id);
    }

    @DeleteMapping("/batch-delete")
    public int employeeBatchDelete(@RequestParam("ids") String ids) {
        return employeeService.batchDelete("\"" + String.join("\",\"", ids.split(",")) + "\"");
    }

    @PutMapping("/update")
    public int employeeUpdate(@RequestBody Employee employee) {
	    return employeeService.update(employee);
    }

    @GetMapping("/find")
    public Employee employeeFind(@RequestParam("id") String id) {
        return employeeService.findById(id);
    }

    @GetMapping("/list-paged/{page-no}/{page-size}")
    public PageInfo<List<Employee>> employeeListPaged(@PathVariable(value="page-no") int
    pageNumber,
                                                      @PathVariable(value="page-size") int pageSize) {
        List<Employee> list = employeeService.list(pageNumber, pageSize);
        return new PageInfo(list);
    }

    @GetMapping("/list")
    public List<Employee> employeeList() {
        return employeeService.list();
    }

    @PostMapping("/find-by-paged/{page-no}/{page-size}")
    public PageInfo<List<Employee>> employeeFindByPaged(@RequestBody Employee
    employee, @PathVariable("page-no") int pageNumber, @PathVariable("page-size") int pageSize) {
        List<Employee> list = employeeService.findBy(employee, pageNumber, pageSize);
        return new PageInfo(list);
    }

    @PostMapping("/find-by")
    public List<Employee> employeeFindByPaged(@RequestBody Employee
    employee) {
        return employeeService.findBy(employee);
    }

    @GetMapping("/find-one")
    public Employee employeeFindOne(@RequestParam("fieldName") String fieldName,
                                    @RequestParam("value") String
    value) {
        return employeeService.findBy(fieldName, value);
    }
}
