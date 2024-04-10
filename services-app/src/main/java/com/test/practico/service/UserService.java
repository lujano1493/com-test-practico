package com.test.practico.service;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.practico.component.JwtUtil;
import com.test.practico.dto.AuthDTO;
import com.test.practico.dto.LoginDTO;
import com.test.practico.dto.RegisterDTO;
import com.test.practico.entity.User;
import com.test.practico.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService  {

	
    private final JwtUtil jwtUtil;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;


    public AuthDTO login(LoginDTO login) throws Exception {
        try {
            authenticate(login.getEmail(), login.getPassword());

            User user = userRepository.findByEmail(login.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            String token = jwtUtil.generateToken(user);
            return new AuthDTO(token);
        } catch (BadCredentialsException | UsernameNotFoundException e) {
            System.out.println(e.getMessage());
            throw new BadCredentialsException("Incorrect username or password");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public AuthDTO register(RegisterDTO register) throws Exception {
        try {
            User user = createUserFromRegistration(register);
            user = userRepository.save(user);

            String token = jwtUtil.generateToken(user);
            return new AuthDTO(token);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    private User createUserFromRegistration(RegisterDTO register) {
        User user = new User();
        user.setName(register.getName());
        user.setLastName(register.getLastName());
        user.setEmail(register.getEmail());
        user.setPassword(passwordEncoder.encode(register.getPassword()));
        user.setRole(register.getRole());

        return user;
    }
}