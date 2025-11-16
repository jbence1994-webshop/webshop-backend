package com.github.jbence1994.webshop.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static com.github.jbence1994.webshop.user.AddressTestObject.addressAfterMappingFromDto;
import static com.github.jbence1994.webshop.user.ChangePasswordRequestTestObject.changePasswordRequest;
import static com.github.jbence1994.webshop.user.ChangePasswordRequestTestObject.notSanitizedChangePasswordRequest;
import static com.github.jbence1994.webshop.user.ForgotPasswordRequestTestObject.forgotPasswordRequest;
import static com.github.jbence1994.webshop.user.ForgotPasswordRequestTestObject.notSanitizedForgotPasswordRequest;
import static com.github.jbence1994.webshop.user.RegistrationRequestTestObject.notSanitizedRegistrationRequest;
import static com.github.jbence1994.webshop.user.RegistrationRequestTestObject.registrationRequest;
import static com.github.jbence1994.webshop.user.RegistrationResponseTestObject.registrationResponse;
import static com.github.jbence1994.webshop.user.ResetPasswordRequestTestObject.notSanitizedResetPasswordRequest;
import static com.github.jbence1994.webshop.user.ResetPasswordRequestTestObject.resetPasswordRequest;
import static com.github.jbence1994.webshop.user.UserDtoTestObject.userDto;
import static com.github.jbence1994.webshop.user.UserTestObject.user1AfterMappingFromDto;
import static com.github.jbence1994.webshop.user.UserTestObject.user1WithoutAvatar;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests {

    @Mock
    private ChangePasswordRequestSanitizer changePasswordRequestSanitizer;

    @Mock
    private ForgotPasswordRequestSanitizer forgotPasswordRequestSanitizer;

    @Mock
    private ResetPasswordRequestSanitizer resetPasswordRequestSanitizer;

    @Mock
    private RegistrationRequestSanitizer registrationRequestSanitizer;

    @Mock
    private UserQueryService userQueryService;

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserController userController;

    @Test
    public void getUserTest_HappyPath() {
        when(userQueryService.getUser(anyLong())).thenReturn(user1WithoutAvatar());
        when(userMapper.toUserDto(any())).thenReturn(userDto());

        var result = userController.getUser(1L);

        assertThat(result.id(), equalTo(userDto().id()));
        assertThat(result.email(), equalTo(userDto().email()));
        assertThat(result.firstName(), equalTo(userDto().firstName()));
        assertThat(result.middleName(), equalTo(userDto().middleName()));
        assertThat(result.lastName(), equalTo(userDto().lastName()));
        assertThat(result.dateOfBirth(), equalTo(userDto().dateOfBirth()));
        assertThat(result.phoneNumber(), equalTo(userDto().phoneNumber()));
        assertThat(result.address().addressLine(), equalTo(userDto().address().addressLine()));
        assertThat(result.address().municipality(), equalTo(userDto().address().municipality()));
        assertThat(result.address().province(), equalTo(userDto().address().province()));
        assertThat(result.address().postalCode(), equalTo(userDto().address().postalCode()));
        assertThat(result.address().country(), equalTo(userDto().address().country()));
    }

    @Test
    public void getUserTest_UnhappyPath_UserNotFoundException() {
        when(userQueryService.getUser(anyLong())).thenThrow(new UserNotFoundException(1L));

        var result = assertThrows(
                UserNotFoundException.class,
                () -> userController.getUser(1L)
        );

        assertThat(result.getMessage(), equalTo("No user was found with the given ID: #1."));
    }

    @Test
    public void registerUserTest() {
        when(registrationRequestSanitizer.sanitize(any())).thenReturn(registrationRequest());
        when(userMapper.toAddress(any())).thenReturn(addressAfterMappingFromDto());
        when(userMapper.toUser(any())).thenReturn(user1AfterMappingFromDto());
        when(userService.registerUser(any())).thenReturn(user1WithoutAvatar());
        when(userMapper.toRegistrationResponse(any())).thenReturn(registrationResponse());

        var result = userController.registerUser(notSanitizedRegistrationRequest());

        assertThat(result.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody().id(), equalTo(registrationResponse().id()));
        assertThat(result.getBody().email(), equalTo(registrationResponse().email()));
    }

    @Test
    public void changePasswordTest() {
        when(changePasswordRequestSanitizer.sanitize(any())).thenReturn(changePasswordRequest());

        var result = userController.changePassword(notSanitizedChangePasswordRequest());

        assertThat(result.getStatusCode(), equalTo(HttpStatus.NO_CONTENT));
        assertThat(result.getBody(), is(nullValue()));
    }

    @Test
    public void forgotPasswordTest() {
        when(forgotPasswordRequestSanitizer.sanitize(any())).thenReturn(forgotPasswordRequest());

        var result = userController.forgotPassword(notSanitizedForgotPasswordRequest());

        assertThat(result.getStatusCode(), equalTo(HttpStatus.ACCEPTED));
        assertThat(result.getBody(), is(nullValue()));
    }

    @Test
    public void resetPasswordTest() {
        when(resetPasswordRequestSanitizer.sanitize(any())).thenReturn(resetPasswordRequest());

        var result = userController.resetPassword(notSanitizedResetPasswordRequest());

        assertThat(result.getStatusCode(), equalTo(HttpStatus.NO_CONTENT));
        assertThat(result.getBody(), is(nullValue()));
    }

    @Test
    public void deleteUserTest() {
        doNothing().when(userService).deleteUser(any());

        var result = userController.deleteUser(1L);

        assertThat(result.getStatusCode(), equalTo(HttpStatus.NO_CONTENT));
        assertThat(result.getBody(), is(nullValue()));
    }
}
