package com.tihanovich.monitorsensors.service;

import com.tihanovich.monitorsensors.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        if ("viewer".equals(login)) {
            return userRepository.findByUserLogin(login)
                    .map(user -> new User(
                            user.getUserLogin(),
                            passwordEncoder.encode(user.getUserPassword()),
                            Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                    ))
                    .orElseThrow(() -> new UsernameNotFoundException("User is not found"));
        } else {
            return userRepository.findByUserLogin(login)
                    .map(user -> new User(
                            user.getUserLogin(),
                            passwordEncoder.encode(user.getUserPassword()),
                            Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
                    ))
                    .orElseThrow(() -> new UsernameNotFoundException("User is not found"));
        }
    }
}