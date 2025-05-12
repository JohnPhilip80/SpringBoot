package com.cca.john.book.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cca.john.book.models.AppRole;
import com.cca.john.book.models.AppUser;
import com.cca.john.book.models.AppUserDto;
import com.cca.john.book.models.AppUserPrincipal;
import com.cca.john.book.models.Book;
import com.cca.john.book.models.BookDto;
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
	/*public void createBook(Book book, boolean isDB) {
		if(!isDB)
			repo.createBook(book);
		else
			repoDB.save(book);
	}*/
	public void createBook(BookDto bookDto, boolean isDB) {
	if(!isDB)
		repo.createBook(new Book(bookDto.getBno(),bookDto.getTitle(),bookDto.getAuthor(),bookDto.getPrice()));
	else
		repoDB.save(new Book(bookDto.getTitle(),bookDto.getAuthor(),bookDto.getPrice()));
	}
	/*public void updateBook(Book book,boolean isDB) {
		if(!isDB)
			repo.updateBook(book);
		else
			repoDB.save(book);
	}*/
	public void updateBook(BookDto bookDto,boolean isDB) {
		if(!isDB)
			repo.updateBook(new Book(bookDto.getBno(),bookDto.getTitle(),bookDto.getAuthor(),bookDto.getPrice()));
		else
			repoDB.save(new Book(bookDto.getTitle(),bookDto.getAuthor(),bookDto.getPrice()));
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
	public AppUser saveUser(AppUserDto appUserDto) {
		AppUser appUser = new AppUser(appUserDto.getName(),appUserDto.getEmail(),appUserDto.getPassword(),Arrays.asList(new AppRole("ROLE_USER")));
		repoUS.save(appUser);
		return appUser;
	}
	public AppUser getAppUserByEmail(String email) {
		return repoUS.getAppUserByEmail(email);
	}
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		//return new AppUserPrincipal(repoUS.getAppUserByEmail(userName));
		AppUser appUser = repoUS.getAppUserByEmail(userName);
		if(appUser == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		return new User(appUser.getEmail(),appUser.getPassword(),null);
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
