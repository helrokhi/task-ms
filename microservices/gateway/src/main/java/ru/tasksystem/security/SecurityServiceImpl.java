package ru.tasksystem.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.tasksystem.security.jwt.JwtUtils;
import ru.tasksystem.web.model.AuthRequest;
import ru.tasksystem.web.model.JwtResponse;
import ru.tasksystem.web.model.SimpleResponse;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class SecurityServiceImpl implements SecurityService {
    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    private final Map<String, String> refreshTokenCache = new HashMap<>();

    @Override
    public JwtResponse authenticateUser(AuthRequest authRequest) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                authRequest.getEmail(), authRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();

        return generateJwtResponse(userDetails);
    }

    @Override
    public SimpleResponse logout(String userName) {

        refreshTokenCache.remove(userName);

        return new SimpleResponse("User " + userName + " has been logged out!");
    }

    private JwtResponse generateJwtResponse(UserDetails userDetails) {
        String accessToken = jwtUtils.generateAccessToken(userDetails);

        String refreshToken = jwtUtils.generateRefreshToken(userDetails);

        refreshTokenCache.put(userDetails.getUsername(), refreshToken);

        JwtResponse jwtResponse =
                JwtResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();

        return jwtResponse;
    }
}
