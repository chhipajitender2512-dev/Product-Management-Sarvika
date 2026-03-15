package com.sarvika.productmanagement.config;


import com.sarvika.productmanagement.entity.User;
import com.sarvika.productmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Slf4j
@Configuration
public class DataSeeder implements ApplicationRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        seedUser();
    }

    private void seedUser() {
        if (!userRepository.existsByUsername("admin")) {
            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin@123"))
                    .build();
            userRepository.save(admin);
            log.info("Admin user seeded successfully");
        } else {
            log.info("Admin user already exists, skipping seed");
        }
    }
}
