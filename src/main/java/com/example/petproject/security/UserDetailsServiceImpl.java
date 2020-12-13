package com.example.petproject.security;

import com.example.petproject.domain.Role;
import com.example.petproject.domain.User;
import com.example.petproject.exeptions.ResourceNotFoundException;
import com.example.petproject.repositories.RoleRepository;
import com.example.petproject.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserDetailsServiceImpl(UserRepository userRepository,
          RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.getByName(username)
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));
        List<Role> roles = roleRepository.getRoles(user.getId());

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), roles);
        return userDetails;
    }
}
