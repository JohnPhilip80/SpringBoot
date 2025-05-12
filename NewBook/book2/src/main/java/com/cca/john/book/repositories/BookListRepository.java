package com.cca.john.book.repositories;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.stereotype.Repository;
import com.cca.john.book.models.Book;

@Repository
public class BookListRepository implements BookCRUD<Book,Long> {
	private String bookName;
	private List<Book> books;
	
	public BookListRepository() {
		bookName = "Spring Boot By John";
		books = new ArrayList<Book>(Arrays.asList(
				new Book(1L,"Java Core","Murgan",650.5),
				new Book(2L,"JSP","Sivan",750.5),
				new Book(3L,"Spring Boot","Raman",950.5),
				new Book(4L,"Hibernate","Saraswathi",850.5)));
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	@Override
	public List<Book> readBooks() {
		return books;
	}
	@Override
	public void createBook(Book book) {
		books.add(book);
	}
	@Override
	public void updateBook(Book book) {
		int index =0;
		for(int i=0;i<books.size();i++) {
			if(books.get(i).getBno() == book.getBno()) {
				index = i;
			}
		}
		books.set(index, book);
	}
	@Override
	public void deleteBook(Long bno) {
		int index =0;
		for(int i=0;i<books.size();i++) {
			if(books.get(i).getBno() == bno) {
				index = i;
			}
		}
		books.remove(index);
	}
	@Override
	public Book getBookByBno(Long bno) {
		int index =0;
		for(int i=0;i<books.size();i++) {
			if(books.get(i).getBno() == bno) {
				index = i;
			}
		}
		return books.get(index);
	}
}
