package com.egorov.chatexample.services;


import com.egorov.chatexample.models.User;
import com.egorov.chatexample.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public User findByUser (String email){
        return userRepository.findByEmail(email);
    }

    public boolean createUser (User user){
        String email = user.getEmail();
        if (userRepository.findByEmail(email)!=null){
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        log.info("Saving new user: username - {}, email - {}", user.getName(), email);
        return true;
    }

}
