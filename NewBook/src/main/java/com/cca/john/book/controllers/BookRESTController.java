package com.cca.john.book.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cca.john.book.models.Book;
import com.cca.john.book.services.BookService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/book/api")
public class BookRESTController {
	@Autowired
	private BookService service;

	@GetMapping("/getbookname")
	public String getBookName(HttpServletRequest req) {
		return service.getBookName() + req.getSession().getId();
	}
	@GetMapping("/readbooks")
	public List<Book> readBooks(@RequestParam boolean db) {
		return service.readBooks(db);
	}
	@PostMapping("/createbook")
	public String createBook(@RequestParam boolean db, @RequestBody Book book) {
		service.createBook(book,db);
		return "Success";
	}
	@PutMapping("/updatebook")
	public String updateBook(@RequestParam boolean db, @RequestBody Book book) {
		service.updateBook(book,db);
		return "Success";
	}
	@DeleteMapping("/deletebook")
	public String deleteBook(@RequestParam boolean db, @RequestParam Long bno) {
		service.deleteBook(bno,db);
		return "Success";
	}
	/*@DeleteMapping("/deletebook/{bno}")
	public void deleteBook(@PathVariable("bno") Long bno) {
		service.deleteBook(bno);
	}*/
	@GetMapping("getbookbybno")
	public Book getBookByBno(@RequestParam boolean db, @RequestParam Long bno) {
		return service.getBookByBno(bno,db);
	}
	@GetMapping("/csrf-token")
	public CsrfToken getCsrfToken(HttpServletRequest req){
		return (CsrfToken) req.getAttribute("_csrf");
	}
}
