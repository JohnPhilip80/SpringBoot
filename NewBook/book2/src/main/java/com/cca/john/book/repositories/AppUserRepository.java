package com.cca.john.book.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cca.john.book.models.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {

}
