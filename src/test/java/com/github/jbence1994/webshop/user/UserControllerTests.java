package com.github.jbence1994.webshop.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static com.github.jbence1994.webshop.user.ChangePasswordRequestTestObject.changePasswordRequest;
import static com.github.jbence1994.webshop.user.RegisterUserRequestTestObject.registerUserRequest;
import static com.github.jbence1994.webshop.user.UserDtoTestObject.userDto;
import static com.github.jbence1994.webshop.user.UserTestObject.user;
import static com.github.jbence1994.webshop.user.UserTestObject.userAfterMappingFromDto;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests {
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
        when(userQueryService.getUser(anyLong())).thenReturn(user());
        when(userMapper.toDto(any())).thenReturn(userDto());

        var result = userController.getUser(1L);

        assertThat(result, allOf(
                hasProperty("id", equalTo(userDto().getId())),
                hasProperty("email", equalTo(userDto().getEmail()))
        ));
        assertThat(result.getProfile(), allOf(
                hasProperty("firstName", equalTo(userDto().getProfile().getFirstName())),
                hasProperty("middleName", equalTo(userDto().getProfile().getMiddleName())),
                hasProperty("lastName", equalTo(userDto().getProfile().getLastName())),
                hasProperty("dateOfBirth", equalTo(userDto().getProfile().getDateOfBirth())),
                hasProperty("phoneNumber", equalTo(userDto().getProfile().getPhoneNumber()))
        ));
        assertThat(result.getProfile().getAddress(), allOf(
                hasProperty("addressLine", equalTo(userDto().getProfile().getAddress().getAddressLine())),
                hasProperty("municipality", equalTo(userDto().getProfile().getAddress().getMunicipality())),
                hasProperty("province", equalTo(userDto().getProfile().getAddress().getProvince())),
                hasProperty("postalCode", equalTo(userDto().getProfile().getAddress().getPostalCode())),
                hasProperty("country", equalTo(userDto().getProfile().getAddress().getCountry()))
        ));
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
        when(userMapper.toEntity(any())).thenReturn(userAfterMappingFromDto());
        when(userService.registerUser(any())).thenReturn(user());
        when(userMapper.toDto(any())).thenReturn(userDto());

        var result = userController.registerUser(registerUserRequest());

        assertThat(result.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody(), allOf(
                hasProperty("id", equalTo(userDto().getId())),
                hasProperty("email", equalTo(userDto().getEmail()))
        ));
    }

    @Test
    public void changePasswordTest() {
        assertDoesNotThrow(() -> userController.changePassword(1L, changePasswordRequest()));
    }

    @Test
    public void deleteUserTest() {
        doNothing().when(userService).deleteUser(any());

        var result = userController.deleteUser(1L);

        assertThat(result.getStatusCode(), equalTo(HttpStatus.NO_CONTENT));
        assertThat(result.getBody(), is(nullValue()));
    }
}
