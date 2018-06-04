package com.prokarma.springmvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HelloWorldController {

//	@RequestMapping(method = RequestMethod.GET)
	@GetMapping		// since Spring 4.3.0
	public String sayHello(ModelMap model) {
		model.addAttribute("greeting", "Hello World from Spring 4 MVC");
		return "welcome";
	}


	@RequestMapping(value="/helloagain", method = RequestMethod.GET)
//	@GetMapping(value ="/helloagain")
	public String sayHelloAgain(ModelMap model) {
		model.addAttribute("greeting", "Hello World Again, from Spring 4 MVC");
		return "welcome";
	}
	
//	http://localhost:9093/Spring4MVCHelloWorldDemo/pathVariable/Prokarma
	@RequestMapping(value="/pathVariable/{name}", method = RequestMethod.GET)
	@ResponseBody
	public String pathVariable(@PathVariable("name") String name) {
		
		return name;
	}
	
//	http://localhost:9093/Spring4MVCHelloWorldDemo/requestParam?name=Prokarma
	@RequestMapping(value="/requestParam", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String requestParam(@RequestParam("name") String name) {
		return name;
	}
	
	@ModelAttribute("countries")
	public List<String> initializeCountries() {
		List<String> countries = new ArrayList<String>();
		countries.add("USA");
		countries.add("CANADA");
		countries.add("FRANCE");
		countries.add("GERMANY");
		countries.add("ITALY");
		countries.add("OTHER");
		return countries;
	}
	
	@RequestMapping(value="/modelAttribute", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<String> modelAttribute(@ModelAttribute("countries") List<String> countries) {
		System.out.println("" + countries);
		return countries;
	}

}
