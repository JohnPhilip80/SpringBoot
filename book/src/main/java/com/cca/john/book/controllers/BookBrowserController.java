package com.cca.john.book.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.cca.john.book.models.Book;
import com.cca.john.book.models.BookDto;
import com.cca.john.book.services.BookService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/book")
public class BookBrowserController {
	@Autowired
	private BookService service;
	
	@GetMapping({"","/"})
	public String showHomePage(Model model,HttpServletRequest req) {
		model.addAttribute("myBook","Spring Boot by John");
		model.addAttribute("mySession","My Session = " + req.getSession().getId());
		return "/book/homePage.html";
	}
	@GetMapping("/readbooks")
	public String readBooks(@RequestParam boolean db,Model model,HttpServletRequest req) {
		List<Book> books = service.readBooks(db);
		model.addAttribute("books",books);
		model.addAttribute("isDB",db);
		model.addAttribute("mySession","My Session = " + req.getSession().getId());
		return "/book/bookList.html";
	}
	@GetMapping("/createbook")
	public String createBook(@RequestParam boolean db,Model model,HttpServletRequest req) {
		model.addAttribute("isDB",db);
		BookDto bookDto = new BookDto();
		model.addAttribute("bookDto",bookDto);
		model.addAttribute("isCreate",true);
		model.addAttribute("mySession","My Session = " + req.getSession().getId());
		return "/book/bookDetail.html";
	}
	@PostMapping("/createbook")
	public String createBook(@RequestParam boolean db,@ModelAttribute BookDto bookDto) {
		Book book = new Book();
		book.setBno(bookDto.getBno());
		book.setTitle(bookDto.getTitle());
		book.setAuthor(bookDto.getAuthor());
		book.setPrice(bookDto.getPrice());
		service.createBook(book, db);
		return "redirect:/book/readbooks?db=" + db;
	}
	@GetMapping("/updatebook")
	public String updateBook(@RequestParam boolean db, Model model, @RequestParam Long bno,HttpServletRequest req) {
		Book book = service.getBookByBno(bno, db);
		BookDto bookDto = new BookDto();
		bookDto.setBno(book.getBno());
		bookDto.setTitle(book.getTitle());
		bookDto.setAuthor(book.getAuthor());
		bookDto.setPrice(book.getPrice());
		model.addAttribute(bookDto);
		model.addAttribute("isDB",db);
		model.addAttribute("isCreate",false);
		model.addAttribute("mySession","My Session = " + req.getSession().getId());
		return "/book/bookDetail.html";
	}
	@PostMapping("/updatebook")
	public String updateBook(@RequestParam boolean db,@RequestParam Long bno, @ModelAttribute BookDto bookDto) {
		Book book = service.getBookByBno(bno, db);
		book.setTitle(bookDto.getTitle());
		book.setAuthor(bookDto.getAuthor());
		book.setPrice(bookDto.getPrice());
		service.updateBook(book, db);
		return "redirect:/book/readbooks?db=" + db;
	}
	@GetMapping("/deletebook")
	public String deleteBook(@RequestParam boolean db,@RequestParam Long bno) {
		service.deleteBook(bno, db);
		return "redirect:/book/readbooks?db=" + db;
	}
}
