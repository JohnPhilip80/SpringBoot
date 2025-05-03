package com.cca.john.student.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cca.john.student.models.Student;
import com.cca.john.student.repositories.StudentRepository;
import com.cca.john.student.repositories.StudentRepositoryDB;

@Service
public class StudentService {
	private StudentRepository repo;
	@Autowired
	private StudentRepositoryDB repoDB;

	public StudentService() {
		super();
		repo = new StudentRepository();
	}
	public String getStudentName() {
		return repo.getStudentName();
	}
	public List<Student> readAllStudents(boolean isDB) {
		if(!isDB)
			return repo.readAllItems();
		else
			return repoDB.findAll();
	}
	public void createStudent(Student student,boolean isDB) {
		if(!isDB)
			repo.createItem(student);
		else
			repoDB.save(student);
	}
	public void updateStudent(Student student, boolean isDB) {
		if(!isDB)
			repo.updateItem(student);
		else
			repoDB.save(student);
	}
	public Student getStudentByRno(Long rno, boolean isDB) {
		if(!isDB)
			return repo.getItemById(rno);
		else
			return repoDB.getByRno(rno);
	}
	public void deleteStudent(Long rno, boolean isDB) {
		if(!isDB)
			repo.deleteItem(rno);
		else
			repoDB.deleteById(rno);
	}
}
