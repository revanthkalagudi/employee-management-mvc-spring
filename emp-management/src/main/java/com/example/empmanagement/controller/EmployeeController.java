package com.example.empmanagement.controller;

import com.example.empmanagement.model.Employee;
import com.example.empmanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/")
    public String viewEmployeeList(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "employee/list";
    }

    @GetMapping("/employee/new")
    public String newEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee/new";
    }

    @PostMapping("/employee/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/";
    }

    @GetMapping("/employee/edit/{id}")
    public String editEmployeeForm(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeRepository.getById(id));
        return "employee/edit";
    }

    @PostMapping("/employee/update/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute("employee") Employee employee) {
        Employee existingEmployee = employeeRepository.getById(id);
        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPassword(employee.getPassword());
        employeeRepository.save(existingEmployee);
        return "redirect:/";
    }

    @GetMapping("/employee/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
        return "redirect:/";
    }
}
