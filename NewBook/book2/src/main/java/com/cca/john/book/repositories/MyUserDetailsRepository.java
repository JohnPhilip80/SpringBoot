package com.cca.john.book.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cca.john.book.models.AppUser;

@Repository
public interface MyUserDetailsRepository extends JpaRepository<AppUser,Long> {
	AppUser getAppUserByEmail(String email);
	AppUser getAppUserById(Long id);
}
