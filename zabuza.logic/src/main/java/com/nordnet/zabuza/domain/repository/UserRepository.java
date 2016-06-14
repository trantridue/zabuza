package com.nordnet.zabuza.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nordnet.zabuza.domain.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

}
