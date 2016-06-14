package com.nordnet.zabuza.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nordnet.zabuza.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
