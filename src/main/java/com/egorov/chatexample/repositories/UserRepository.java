package com.egorov.chatexample.repositories;

import com.egorov.chatexample.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository <User, Long> {

     User findByEmail(String email);
}
