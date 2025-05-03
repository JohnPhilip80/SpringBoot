package com.cca.john.student.models;

public class StudentDto {
	private Long rno;
	private String name;
	private String course;
	private Double percentage;
	public StudentDto(Long rno, String name, String course, Double percentage) {
		super();
		this.rno = rno;
		this.name = name;
		this.course = course;
		this.percentage = percentage;
	}
	public StudentDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getRno() {
		return rno;
	}
	public void setRno(Long rno) {
		this.rno = rno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
}
