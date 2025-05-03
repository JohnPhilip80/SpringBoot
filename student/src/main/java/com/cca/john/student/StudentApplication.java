package com.cca.john.student;

import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.cca.john.student.controllers.StudentConsoleController;
import com.cca.john.student.models.Student;

@SpringBootApplication
public class StudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
		StudentConsoleController ctr = new StudentConsoleController();
		studentList(ctr);
		deleteStudent(ctr);
		studentList(ctr);
	}
	public static void studentList(StudentConsoleController ctr) {
		List<Student> students = ctr.readAllStudents(false);
		System.out.println("Rno --  Name  -- Course  --   Percentage");
		for(int i=0;i<students.size();i++) {
			System.out.println(students.get(i).getRno() + " -- " + 
								students.get(i).getName() + " -- " +
								students.get(i).getCourse() + " -- " +
								students.get(i).getPercentage());
		}
	}
	public static void createStudent(StudentConsoleController ctr) {
		Student student = new Student(5l,"Krishnan","Block Chain",92.6);
		ctr.createStudent(student,false);
	}
	public static void updateStudent(StudentConsoleController ctr) {
		List<Student> students = ctr.readAllStudents(false);
		Student student = students.get(2);
		student.setName("Sekhar");
		student.setCourse("Angular");
		student.setPercentage(71.5);
		ctr.updateStudent(student,false);
	}
	public static void deleteStudent(StudentConsoleController ctr) {
		ctr.deleteStudent(2l,false);
	}
}
