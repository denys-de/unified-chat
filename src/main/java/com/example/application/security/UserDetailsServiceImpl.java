package com.example.application.security;

import java.util.stream.Collectors;

import com.example.application.chat.repository.UserJpaRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Denys Babich
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserJpaRepository userJpaRepository;

    public UserDetailsServiceImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userJpaRepository.findByUsername(username).map(user -> new User(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet()))).orElseThrow(() -> new UsernameNotFoundException(username));
    }

}
