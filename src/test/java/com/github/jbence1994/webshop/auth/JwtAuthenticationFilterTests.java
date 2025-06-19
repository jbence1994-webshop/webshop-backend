package com.github.jbence1994.webshop.auth;

import com.github.jbence1994.webshop.user.Role;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

import static com.github.jbence1994.webshop.auth.AuthTestConstants.BEARER_TOKEN;
import static com.github.jbence1994.webshop.auth.AuthTestConstants.INVALID_BEARER_TOKEN;
import static com.github.jbence1994.webshop.auth.AuthTestConstants.MALFORMED_BEARER_TOKEN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JwtAuthenticationFilterTests {

    @Mock
    private JwtService jwtService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private JwtAuthenticationFilter filter;

    @BeforeEach
    void setup() {
        SecurityContextHolder.clearContext();
    }

    @Test
    public void doFilterInternalTest_HappyPath() throws ServletException, IOException {
        when(request.getHeader("Authorization")).thenReturn(BEARER_TOKEN);

        var parsedJwt = mock(Jwt.class);
        when(jwtService.parseToken(any())).thenReturn(parsedJwt);
        when(jwtService.getRoleFromToken(any())).thenReturn(Role.ADMIN);
        when(jwtService.getUserIdFromToken(any())).thenReturn(1L);
        when(parsedJwt.isExpired()).thenReturn(false);

        filter.doFilterInternal(request, response, filterChain);

        verify(jwtService).parseToken(any());
        verify(parsedJwt).isExpired();

        var auth = SecurityContextHolder.getContext().getAuthentication();
        assertThat(auth, is(instanceOf(UsernamePasswordAuthenticationToken.class)));
        assertThat(auth.getPrincipal(), equalTo(1L));
        assertThat(auth.getCredentials(), is(nullValue()));
        assertThat(auth.getAuthorities(), not(empty()));
        assertThat(auth.getAuthorities().size(), equalTo(1));

        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void doFilterInternalTest_UnhappyPath_NoAuthorizationHeader() throws ServletException, IOException {
        when(request.getHeader("Authorization")).thenReturn(null);

        filter.doFilterInternal(request, response, filterChain);

        assertThat(SecurityContextHolder.getContext().getAuthentication(), is(nullValue()));

        verify(filterChain).doFilter(request, response);
        verifyNoInteractions(jwtService);
    }

    @Test
    public void doFilterInternalTest_UnhappyPath_MalformedAuthorizationHeader() throws ServletException, IOException {
        when(request.getHeader("Authorization")).thenReturn(MALFORMED_BEARER_TOKEN);

        filter.doFilterInternal(request, response, filterChain);

        assertThat(SecurityContextHolder.getContext().getAuthentication(), is(nullValue()));

        verify(filterChain).doFilter(request, response);
        verifyNoInteractions(jwtService);
    }

    @Test
    public void doFilterInternalTest_UnhappyPath_parseTokenFailure() throws ServletException, IOException {
        when(request.getHeader("Authorization")).thenReturn(INVALID_BEARER_TOKEN);

        when(jwtService.parseToken(any())).thenReturn(null);

        filter.doFilterInternal(request, response, filterChain);

        assertThat(SecurityContextHolder.getContext().getAuthentication(), is(nullValue()));

        verify(jwtService).parseToken(any());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void doFilterInternalTest_UnhappyPath_TokenIsExpired() throws ServletException, IOException {
        when(request.getHeader("Authorization")).thenReturn(BEARER_TOKEN);

        var parsedJwt = mock(Jwt.class);
        when(jwtService.parseToken(any())).thenReturn(parsedJwt);
        when(parsedJwt.isExpired()).thenReturn(true);

        filter.doFilterInternal(request, response, filterChain);

        assertThat(SecurityContextHolder.getContext().getAuthentication(), is(nullValue()));

        verify(jwtService).parseToken(any());
        verify(parsedJwt).isExpired();
        verify(filterChain).doFilter(request, response);
    }
}
