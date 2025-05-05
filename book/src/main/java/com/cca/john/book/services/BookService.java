package com.cca.john.book.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cca.john.book.models.Book;
import com.cca.john.book.repositories.BookDBRepository;
import com.cca.john.book.repositories.BookListRepository;

@Service
public class BookService {
	@Autowired
	private BookListRepository repo;
	@Autowired
	private BookDBRepository repoDB;
	
	public String getBookName() {
		return repo.getBookName();
	}
	public List<Book> readBooks(boolean isDB){
		if(!isDB)
			return repo.readBooks();
		else
			return repoDB.findAll();
	}
	public void createBook(Book book, boolean isDB) {
		if(!isDB)
			repo.createBook(book);
		else
			repoDB.save(book);
	}
	public void updateBook(Book book,boolean isDB) {
		if(!isDB)
			repo.updateBook(book);
		else
			repoDB.save(book);
	}
	public void deleteBook(Long bno,boolean isDB) {
		if(!isDB)
			repo.deleteBook(bno);
		else
			repoDB.deleteById(bno);
	}
	public Book getBookByBno(Long bno,boolean isDB) {
		if(!isDB)
			return repo.getBookByBno(bno);
		else
			return repoDB.getBookByBno(bno);
	}
}
