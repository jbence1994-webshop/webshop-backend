package com.github.jbence1994.webshop.auth;

import com.github.jbence1994.webshop.user.User;
import com.github.jbence1994.webshop.user.UserQueryService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserIdentityMapper userIdentityMapper;
    private final UserQueryService userQueryService;
    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userQueryService.getUser(request.getEmail());
        var userIdentity = userIdentityMapper.toUserIdentity(user);

        var accessToken = jwtService.generateAccessToken(userIdentity);
        var refreshToken = jwtService.generateRefreshToken(userIdentity);

        return new LoginResponse(accessToken, refreshToken);
    }

    @Override
    public Jwt refreshAccessToken(String refreshToken) {
        var jwt = jwtService.parseToken(refreshToken);

        if (jwt == null || jwt.isExpired()) {
            throw new BadCredentialsException("Invalid refresh token.");
        }

        var user = userQueryService.getUser(jwt.getUserId());
        var userIdentity = userIdentityMapper.toUserIdentity(user);

        return jwtService.generateAccessToken(userIdentity);
    }

    @Override
    public User getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (
                authentication == null ||
                        !authentication.isAuthenticated() ||
                        authentication.getPrincipal().equals("anonymousUser")
        ) {
            throw new AccessDeniedException("Access denied.");
        }

        var userId = (Long) authentication.getPrincipal();

        return userQueryService.getUser(userId);
    }
}
