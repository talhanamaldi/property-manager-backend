package org.app.property_manager.service.implementation;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.app.property_manager.application.config.AppUserDetails;
import org.app.property_manager.application.config.JwtService;
import org.app.property_manager.common.exception.GeneralException;
import org.app.property_manager.common.exception.GeneralWarning;
import org.app.property_manager.model.entity.app.User;
import org.app.property_manager.repository.UserRepository;
import org.app.property_manager.service.AuthService;
import org.app.property_manager.service.dto.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponse signup(User user) throws GeneralException, GeneralWarning {
        if (user.getEmail() == null || user.getPassword() == null) {
            throw new GeneralException("Email and password are required");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new GeneralException("Email already registered");
        }

        User u = new User();
        u.setEmail(user.getEmail());
        u.setUsername(user.getUsername());
        u.setPassword(passwordEncoder.encode(user.getPassword()));
        u.setIsAdmin(user.getIsAdmin());
        userRepository.save(u);

        Map<String, Object> claims = new HashMap<>();
        claims.put("userName", u.getUsername());
        claims.put("isAdmin", u.getIsAdmin());

        String token = jwtService.generate(new AppUserDetails(u), claims);
        Instant exp = jwtService.getExpiry(token);
        return new AuthResponse(token, exp);
    }

    @Override
    public AuthResponse signin(User user) throws GeneralException, GeneralWarning {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
            );
        } catch (Exception e) {
            throw new GeneralException("Invalid credentials");
        }

        User u = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new GeneralWarning("User not found"));

        Map<String, Object> claims = new HashMap<>();
        claims.put("userName", u.getUsername());
        claims.put("isAdmin", u.getIsAdmin());

        String token = jwtService.generate(new AppUserDetails(u), claims);
        Instant exp = jwtService.getExpiry(token);
        return new AuthResponse(token, exp);
    }
}