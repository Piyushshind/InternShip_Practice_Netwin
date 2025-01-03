package com.library.system.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.system.DTO.LoginResponseDTO;
import com.library.system.DTO.UserTemlate;
import com.library.system.Entities.User;
import com.library.system.Entities.userRole;
import com.library.system.Services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    // @Autowired
    // private authenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
        Optional<User> user = userService.findUserByUsername(loginRequest.getUsername());
        if (user.isPresent() && user.get().getPassword().equals(loginRequest.getPassword())) {
            userRole role = user.get().getUserRole();
            Long userId = user.get().getUserId();
            String authToken = "sampleAuthToken";

            String message = "Login successful! Role: " + role;

            return ResponseEntity.ok(new LoginResponseDTO(authToken, role, message, userId));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    // User Registration
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserTemlate userDTO) {
        try {
            userService.registerUser(userDTO);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/getAllUser")
    public List<User> getAllUser() {
        return userService.getAllUserDetails();
    }

}
