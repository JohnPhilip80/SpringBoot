package com.cca.john.book.repositories;

import java.util.List;

public interface BookCRUD<T1,T2> {
	List<T1> readBooks();
	void createBook(T1 book);
	void updateBook(T1 book);
	void deleteBook(T2 bno);
	T1 getBookByBno(T2 bno);
}
