package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.repository.UserRepository;
import edu.baylor.cs.beargo.security.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@Slf4j
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UsernameNotFoundException("Username: " + username);
        }
    }

    // Log in user
    public String login(User user) {
        String username = user.getUsername();
        String password = user.getPassword();

        User authenticatedUser = (User) authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password))
                .getPrincipal();
        log.info("User authenticated: " + authenticatedUser.getUsername());

        return jwtTokenProvider.createToken(username, user.getRoles());
    }

    // Register user
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user exists for given id");
        }
    }

    // TODO: Ban a user for 6 months or a year or forever from the system - Maisha
}