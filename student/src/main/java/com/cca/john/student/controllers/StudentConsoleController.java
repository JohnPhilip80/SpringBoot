package com.cca.john.student.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.cca.john.student.models.Student;
import com.cca.john.student.services.StudentService;

public class StudentConsoleController {
	@Autowired
	private StudentService service;

	public StudentConsoleController() {
		super();
		service = new StudentService();
	}
	public String getStudentName() {
		return service.getStudentName();
	}
	public List<Student> readAllStudents(boolean isDB) {
			return service.readAllStudents(isDB);
	}
	public void createStudent(Student student,boolean isDB) {
		service.createStudent(student,isDB);
	}
	public void updateStudent(Student student,boolean isDB) {
		service.updateStudent(student,isDB);
	}
	public void deleteStudent(Long rno,boolean isDB) {
		service.deleteStudent(rno,isDB);
	}
}
