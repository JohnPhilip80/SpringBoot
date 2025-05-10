package com.cca.john.book.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cca.john.book.models.AppUser;
import com.cca.john.book.models.AppUserPrincipal;
import com.cca.john.book.models.Book;
import com.cca.john.book.repositories.BookDBRepository;
import com.cca.john.book.repositories.BookListRepository;
import com.cca.john.book.repositories.MyUserDetailsRepository;

@Service
public class BookService implements UserDetailsService {
	@Autowired
	private BookListRepository repo;
	
	@Autowired
	private BookDBRepository repoDB;
	
	@Autowired
	private MyUserDetailsRepository repoUS;
	
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
	
	public AppUser getAppUserByEmail(String email) {
		return repoUS.getAppUserByEmail(email);
	}
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		return new AppUserPrincipal(repoUS.getAppUserByEmail(userName));
	}
	public AppUser getAppUserById(Long id) {
		return repoUS.getAppUserById(id);
	}
	public void createUser(AppUser appUser) {
		repoUS.save(appUser);
	}
	public void updateUser(AppUser appUser) {
		repoUS.save(appUser);
	}
	public void deleteUser(Long id) {
		repoUS.deleteById(id);
	}
}
