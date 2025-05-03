package com.cca.john.student.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cca.john.student.models.Student;
import com.cca.john.student.models.StudentDto;
import com.cca.john.student.services.StudentService;



@Controller
@RequestMapping("/student")
public class StudentUIController {
	@Autowired
	private StudentService service;
	public boolean isDB = true;
	public boolean isNew = true;
	public StudentUIController() {
		super();
		//service = new StudentService();
	}
	@GetMapping({"/",""})
	public String showHomePage(Model model) {
		String nameDto = "Welcome John Philip";
		model.addAttribute("nameDto",nameDto);
		return "/student/homePage";
	}
	@GetMapping("/studentlist")
	public String studentList(Model model) {
		List<Student> students = service.readAllStudents(false);
		model.addAttribute("students",students);
		isDB = false;
		model.addAttribute("isDB",isDB);
		return "/student/studentList";
	}
	@GetMapping("/createstudent")
	public String createStudent(Model model) {
		StudentDto studentDto	 = new StudentDto();
		model.addAttribute("studentDto",studentDto);
		isDB = false;
		model.addAttribute("isDB",isDB);
		isNew = true;
		model.addAttribute("isNew",isNew);
		return "student/studentDetail";
	}
	@PostMapping("/createstudent")
	public String createStudent(@ModelAttribute StudentDto studentDto) {
		Student student = new Student();
		student.setRno(studentDto.getRno());
		student.setName(studentDto.getName());
		student.setCourse(studentDto.getCourse());
		student.setPercentage(studentDto.getPercentage());
		service.createStudent(student,false);
		return "redirect:/student/studentlist";
	}
	@GetMapping("/updatestudent")
	public String updateStudent(Model model,@RequestParam("rno") Long rno) {
			Student student = service.getStudentByRno(rno,false);
			StudentDto studentDto = new StudentDto();
			studentDto.setRno(student.getRno());
			studentDto.setName(student.getName());
			studentDto.setCourse(student.getCourse());
			studentDto.setPercentage(student.getPercentage());
			model.addAttribute("studentDto",studentDto);
			isDB = false;
			model.addAttribute("isDB",isDB);
			isNew = false;
			model.addAttribute("isNew",isNew);
			return "student/studentDetail";
	}
	@PostMapping("/updatestudent")
	public String updateStudent(@RequestParam("rno") Long rno, @ModelAttribute StudentDto studentDto) {
			Student student = service.getStudentByRno(rno,false);
			student.setName(studentDto.getName());
			student.setCourse(studentDto.getCourse());
			student.setPercentage(studentDto.getPercentage());
			service.updateStudent(student,false);
			return "redirect:/student/studentlist";
	}
	@GetMapping("/deletestudent")
	public String deleteStudent(@RequestParam("rno") Long rno) {
		service.deleteStudent(rno,false);
		return "redirect:/student/studentlist";
	}
	
	@GetMapping("/db/studentlist")
	public String studentListDB(Model model) {
		List<Student> students = service.readAllStudents(true);
		model.addAttribute("students",students);
		isDB = true;
		model.addAttribute("isDB",isDB);
		return "/student/studentList";
	}
	@GetMapping("/db/createstudent")
	public String createStudentDB(Model model) {
		StudentDto studentDto	 = new StudentDto();
		model.addAttribute("studentDto",studentDto);
		isDB = true;
		model.addAttribute("isDB",isDB);
		isNew = true;
		model.addAttribute("isNew",isNew);
		return "student/studentDetail";
	}
	@PostMapping("/db/createstudent")
	public String createStudentDB(@ModelAttribute StudentDto studentDto) {
		Student student = new Student();
		student.setName(studentDto.getName());
		student.setCourse(studentDto.getCourse());
		student.setPercentage(studentDto.getPercentage());
		service.createStudent(student,true);
		return "redirect:/student/db/studentlist";
	}
	@GetMapping("/db/updatestudent")
	public String updateStudentDB(Model model,@RequestParam("rno") Long rno) {
			Student student = service.getStudentByRno(rno,true);
			StudentDto studentDto = new StudentDto();
			studentDto.setRno(student.getRno());
			studentDto.setName(student.getName());
			studentDto.setCourse(student.getCourse());
			studentDto.setPercentage(student.getPercentage());
			model.addAttribute("studentDto",studentDto);
			isDB = true;
			model.addAttribute("isDB",isDB);
			isNew = false;
			model.addAttribute("isNew",isNew);
			return "student/studentDetail";
	}
	@PostMapping("/db/updatestudent")
	public String updateStudentDB(@RequestParam("rno") Long rno, @ModelAttribute StudentDto studentDto) {
			Student student = service.getStudentByRno(rno,true);
			student.setName(studentDto.getName());
			student.setCourse(studentDto.getCourse());
			student.setPercentage(studentDto.getPercentage());
			
			System.out.println(student.getRno() + " -- " + 
					student.getName() + " -- " +
					student.getCourse() + " -- " +
					student.getPercentage());
			
			service.updateStudent(student,true);
			return "redirect:/student/db/studentlist";
	}
	@GetMapping("/db/deletestudent")
	public String deleteStudentDB(@RequestParam("rno") Long rno) {
		service.deleteStudent(rno,true);
		return "redirect:/student/db/studentlist";
	}
}
