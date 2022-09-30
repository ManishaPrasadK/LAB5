package com.greatlearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.model.Student;
import com.greatlearning.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	public StudentService studentService;
	
	@RequestMapping("/list")
	public String listBooks(Model theModel) {
		System.out.println("request received");
		List<com.greatlearning.model.Student> student=studentService.findAll();
		theModel.addAttribute("Students", student);
		return "list-Students";
		
	}
	
	@RequestMapping("/showFormToAdd")
	public String showFormToAdd(Model theModel) {
		Student student = new Student();
		theModel.addAttribute("Student", student);
		return "Student-form";
	}
	
	@RequestMapping("/showFormToUpdate")
	public String showFromToUpdate(@RequestParam("studentId") int id, Model theModel) {
		Student student=studentService.findById(id);
		theModel.addAttribute("Student", student);
		return "Student-form";
	}
	
	@PostMapping("/save")
	public String saveStudent(@RequestParam("id") int id,@RequestParam("name") String name,@RequestParam("department") String department, @RequestParam("country") String country) {
		System.out.println(id);
		Student student;
		if(id!=0) {
			student=studentService.findById(id);
			student.setName(name);
			student.setDepartment(department);
			student.setCountry(country);
		}
		else
			student=new Student(id,name,department,country);
		studentService.save(student);
		return "redirect:/student/list";
		
	}
	@RequestMapping("/delete")
	public String delete(@RequestParam("studentId") int id) {
		studentService.deleteById(id);
		return"redirect:/student/list";
	}
	
	
	

}
