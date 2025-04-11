package com.individual.online_store.services;


import com.individual.online_store.dtos.UserDto;
import com.individual.online_store.entities.Role;
import com.individual.online_store.entities.User;
import com.individual.online_store.repositories.RoleRepository;
import com.individual.online_store.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserDto::toDto)
                .toList();
    }

    public User create(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return null;
        } else if (user.getRole() == null) {
            Role defaultRole = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Default role not found"));
            user.setRole(defaultRole);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        return userRepository.save(user);
    }

    public User createAdmin(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return null;
        } else {
            Role defaultRole = roleRepository.findByName("ROLE_ADMIN")
                    .orElseThrow(() -> new RuntimeException("Admin role not found"));
            user.setRole(defaultRole);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        return userRepository.save(user);
    }

    public UserDto update(UserDto userDto) {
        if (userDto.getUserId() != null) {
            User user = userRepository.findById(userDto.getUserId())
                    .orElseThrow();
            user.setPassword(user.getPassword());
            user.setActive(user.getActive());
            return UserDto.toDto(user);
        } else {
            return null;
        }
    }

    public ResponseEntity<?> login(String email, String password) {
        try {
            Authentication authentication =
                    authenticationManager.
                            authenticate(new UsernamePasswordAuthenticationToken(email, password));
            return ResponseEntity.ok(authentication.isAuthenticated());
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getLocalizedMessage());
        }
    }

    public User findLoggedInUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(username).orElseThrow();
    }

    @PostConstruct
    private void writeAdmin() {
        if (userRepository.findByEmail("admin").isEmpty()) {
            User user = new User();
            Role defaultRole = roleRepository.findByName("ROLE_ADMIN")
                    .orElseThrow(() -> new RuntimeException("Admin role not found"));
            user.setRole(defaultRole);
            user.setActive(true);
            user.setEmail("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            userRepository.save(user);
        }
    }
}
