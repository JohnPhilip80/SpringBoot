package com.cca.john.book.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cca.john.book.models.Book;
@Repository
public interface BookDBRepository extends JpaRepository<Book,Long>{
	Book getBookByBno(Long bno);
}
