package com.cca.john.student.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.cca.john.student.models.Student;
import com.cca.john.student.services.StudentService;


@RestController
@RequestMapping("/student/api")
public class StudentRESTController {
	@Autowired
	private StudentService service;
	
	public StudentRESTController() {
		super();
		service = new StudentService();
	} 
	
	@GetMapping("/studentname")
	public String getStudentName() {
		return "Hello " +  service.getStudentName();
	}
	@GetMapping("/studentlist")
	public List<Student> studentList() {
		return service.readAllStudents(false);
	}
	@PostMapping("/createstudent")
	public String createStudent(@RequestBody Student student) {
		service.createStudent(student,false);
		return "Success";
	}
	@PutMapping("/updatestudent")
	public String updateStudent(@RequestBody Student student) {
		service.updateStudent(student,false);
		return "Success";
	}
	@DeleteMapping("/deletestudent/{rno}")
	public void deleteStudent(@PathVariable("rno") Long rno) {
		service.deleteStudent(rno,false);
	}
	
	@GetMapping("/db/studentlist")
	public List<Student> studentListDB() {
		return service.readAllStudents(true);
	}
	@PostMapping("/db/createstudent")
	public String createStudentDB(@RequestBody Student student) {
		service.createStudent(student,true);
		return "Success";
	}
	@PutMapping("/db/updatestudent")
	public String updateStudentDB(@RequestBody Student student) {
		service.updateStudent(student,true);
		return "Success";
	}
	@DeleteMapping("/db/deletestudent/{rno}")
	public void deleteStudentDB(@PathVariable("rno") Long rno) {
		service.deleteStudent(rno,true);
	}
}
