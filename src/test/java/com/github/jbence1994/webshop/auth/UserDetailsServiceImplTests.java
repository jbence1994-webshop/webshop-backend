package com.github.jbence1994.webshop.auth;

import com.github.jbence1994.webshop.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static com.github.jbence1994.webshop.user.UserTestConstants.EMAIL;
import static com.github.jbence1994.webshop.user.UserTestObject.user;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImplTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test
    void loadUserByUsername_HappyPath() {
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(user()));

        var result = userDetailsService.loadUserByUsername(EMAIL);

        assertThat(result, allOf(
                hasProperty("username", equalTo(user().getEmail())),
                hasProperty("password", equalTo(user().getPassword()))
        ));
        assertThat(result.getAuthorities(), is(empty()));

        verify(userRepository).findByEmail(any());
    }

    @Test
    void loadUserByUsername_UnhappyPath() {
        when(userRepository.findByEmail(any())).thenReturn(Optional.empty());

        var result = assertThrows(
                UsernameNotFoundException.class,
                () -> userDetailsService.loadUserByUsername(EMAIL));

        assertThat(result.getMessage(), equalTo(EMAIL));

        verify(userRepository).findByEmail(any());
    }
}
