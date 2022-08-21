package com.example.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Employee;
import com.example.demo.repository.WebRepository;

@Controller
public class WebController {
	@Autowired
	WebRepository repo;
	
	@RequestMapping("/")
	public String indexpage(Model model)
	{
		//List<Employee emp=(List<Employee>) repo.findAll();
		model.addAttribute("emplist", repo.findAll());
		return "index";
		//return emp;
	}
	
	@RequestMapping("/new")
	public String newformforemployee(Model model)
	{
		Employee emp=new Employee();
		model.addAttribute("employee", emp);
		return "new_emp";
	}
	
	@PostMapping("/add")
	public String addemployee(Employee emp)
	{
		repo.save(emp);
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView editform(@PathVariable("id")int id)
	{
		ModelAndView mv=new ModelAndView("edit_emp");
		Employee emp= repo.findById(id).orElse(null);
		mv.addObject("employee", emp);
		return mv;
		
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteemp(@PathVariable("id")int id)
	{
		repo.deleteById(id);
		return "redirect:/";
	}
	
	

}
