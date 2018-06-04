package com.prokarma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.prokarma.pojo.Employee;
import com.prokarma.service.Service;

@Controller
public class ControllerClass {
	@Autowired
	private Service service;

	@RequestMapping("/empForm")
	public ModelAndView empForm() {
		return new ModelAndView("empForm", "Employee", new Employee());
	}

	@RequestMapping("/empView")
	public ModelAndView viewemp() {
		List<Employee> list = service.getList();
		return new ModelAndView("empView", "list", list);
	}

	@RequestMapping(value ="/save", method = RequestMethod.POST)
	public ModelAndView saveEmployee(@ModelAttribute("employee") Employee employee) {
		service.addEmp(employee);
		return new ModelAndView("redirect:/empView");
	}
	@RequestMapping(value="/getEmp/{id}")  
    public ModelAndView getEmpById(@PathVariable int id){  
		Employee list=service.getEmpById(id);  
        return new ModelAndView("updateEmpForm","command",list);  
    } 
	@RequestMapping(value="/deleteEmp/{id}")  
    public ModelAndView deleteEmp(@PathVariable int id){  
		service.deleteEmp(id);
		return new ModelAndView("redirect:/empView");
    }
	
	
	@RequestMapping(value="/getEmp/update",method=RequestMethod.POST)  
    public ModelAndView editEmp(@ModelAttribute("employee") Employee employee){  
		service.updateEmpById(employee);
		return new ModelAndView("redirect:/empView");
    }
}
