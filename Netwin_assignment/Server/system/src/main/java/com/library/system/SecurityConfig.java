// package com.library.system;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;

// import io.swagger.v3.oas.models.OpenAPI;
// import io.swagger.v3.oas.models.info.Info;

// @Configuration
// public class SecurityConfig {

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http.csrf().disable()
//             .authorizeRequests(auth -> auth
//                 .requestMatchers("/api/books/**").hasAnyRole("Admin", "Visitor")
//                 .requestMatchers("/api/authors/**", "/api/genres/**", "/api/users/**").hasRole("Admin")
//                 .requestMatchers("/api/reviews/**").hasAnyRole("Admin", "Visitor")
//             )
//             .httpBasic(); // Basic HTTP Authentication
//         return http.build();
//     }

//     @Bean
//     public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
//         UserDetails admin = User.builder()
//             .username("admin")
//             .password(passwordEncoder.encode("password")) // Using password encoder
//             .roles("Admin", "Visitor")
//             .build();

//         UserDetails visitor = User.builder()
//             .username("visitor")
//             .password(passwordEncoder.encode("password"))
//             .roles("Visitor")
//             .build();

//         return new InMemoryUserDetailsManager(admin, visitor);
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder(); // Using BCryptPasswordEncoder
//     }

//     @Bean
//     public OpenAPI customOpenAPI() {
//         return new OpenAPI()
//             .info(new Info().title("Book Library API").version("1.0").description("API for managing books, authors, genres, reviews, and users"));
//     }
// }
