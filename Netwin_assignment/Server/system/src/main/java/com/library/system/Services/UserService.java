package com.library.system.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.system.DTO.UserTemlate;
import com.library.system.Entities.User;
import com.library.system.Repository.UserRepository;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public List<User> getAllUserDetails() {
        return userRepository.findAll();
    }
   

  
    public User registerUser(UserTemlate userDTO) {
      
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

 
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword()); 
        user.setUserEmail(userDTO.getUserEmail());
        user.setUserRole(userDTO.getRole());

        return userRepository.save(user);
    }
}
