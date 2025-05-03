package com.cca.john.student.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cca.john.student.models.Student;

@Repository
public interface StudentRepositoryDB extends JpaRepository<Student,Long> {
	public Student getByRno(Long rno);
}
