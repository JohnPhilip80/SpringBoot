package com.cca.john.student.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cca.john.student.models.CRUDoperations;
import com.cca.john.student.models.Student;

public class StudentRepository implements CRUDoperations<Student,Long> {
	private String studentName;
	private List<Student> students = null;
	
	public StudentRepository() {
		super();
		studentName = "John";
		students = new ArrayList<Student>(Arrays.asList(
				new Student(1L,"Murgan","Java Core",65.5),
				new Student(2L,"Sivan","JSP",75.5),
				new Student(3L,"Raman","Spring Boot",95.5),
				new Student(4L,"Saraswathi","Hibernate",85.5)));
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	@Override
	public void createItem(Student student) {
		students.add(student);
	}
	@Override
	public List<Student> readAllItems() {
		return students;
	}
	@Override
	public void updateItem(Student student) {
		int index =0;
		for(int i=0;i<students.size();i++) {
			if(students.get(i).getRno() == student.getRno()) {
				index = i;
			}
		}
		students.set(index, student);
		
	}
	@Override
	public void deleteItem(Long rno) {
		int index =0;
		for(int i=0;i<students.size();i++) {
			if(students.get(i).getRno() == rno) {
				index = i;
			}
		}
		students.remove(index);
	}
	@Override
	public Student getItemById(Long rno) {
		int index =0;
		for(int i=0;i<students.size();i++) {
			if(students.get(i).getRno() == rno) {
				index = i;
			}
		}
		return students.get(index);
	}
	
}
