package com.example.taskmanagement.services.impls;

import com.example.taskmanagement.dtos.AuthenticationRequest;
import com.example.taskmanagement.dtos.AuthenticationResponse;
import com.example.taskmanagement.dtos.RegisterRequest;
import com.example.taskmanagement.exceptions.EntityFoundException;
import com.example.taskmanagement.exceptions.EntityNotFoundException;
import com.example.taskmanagement.exceptions.NotAuthorizedException;
import com.example.taskmanagement.helpers.UserHelper;
import com.example.taskmanagement.models.User;
import com.example.taskmanagement.repositories.UserRepository;
import com.example.taskmanagement.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserHelper userHelper;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest req) {
        // Retrieve the user
        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("User with email: %s not found", req.getEmail())));
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
            );
        } catch (NotAuthorizedException ex) {
            throw new NotAuthorizedException("Authentication failed");
        }
        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }


    @Override
    public AuthenticationResponse register(RegisterRequest req) {
        Optional<User> existingUser = userRepository.findByEmail(req.getEmail());
        if (existingUser.isPresent()) {
            throw new EntityFoundException(String.format("User with email: %s already exists", req.getEmail()));
        }
        User user = userHelper.createUserWithRole(req, "ADMIN");
        return userHelper.authenticationResponse(user);
    }
}
