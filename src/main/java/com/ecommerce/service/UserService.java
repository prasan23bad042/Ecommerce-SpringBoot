package com.ecommerce.service;

import com.ecommerce.entity.User;
import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.*;
import org.springframework.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.Optional;

@Service
@Transactional //All ur operations is atomic(All or nothing behaviour)
public class UserService implements UserDetailsService {
    
    @Autowired //create proxy object automatically
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        // Hash the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User createUser(User user) {
        if(userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if(userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("Email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User createdUser = userRepository.save(user);
        System.out.println("User created successfully");
        return createdUser;
    }

    public User updateUser(Long id, User updatedUser){
        if(!userRepository.existsById(id)){
            throw new RuntimeException("User not found with id: " + id);
        }
        User existingUser = userRepository.findById(id).orElseThrow(
            () -> new RuntimeException("User not found with id: " + id));

        if(!existingUser.getUsername().equals(updatedUser.getUsername()) && userRepository.existsByUsername(updatedUser.getUsername())){
            throw new RuntimeException("Username already taken");
        }
        if(!existingUser.getEmail().equals(updatedUser.getEmail()) && userRepository.existsByEmail(updatedUser.getEmail())){
            throw new RuntimeException("Email already taken");
        }
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        if(!updatedUser.getPassword().isEmpty()){
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
        return userRepository.save(existingUser);
    }

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(identifier)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email: " + identifier));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), List.of());
}
}
