package com.github.jbence1994.webshop.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.user.UserDtoTestObject.userDto;
import static com.github.jbence1994.webshop.user.UserTestObject.user;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests {
    @Mock
    private UserQueryService userQueryService;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserController userController;

    @Test
    public void getUserTest_HappyPath() {
        when(userQueryService.getUser(any())).thenReturn(user());
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
        when(userQueryService.getUser(any())).thenThrow(new UserNotFoundException(1L));

        var result = assertThrows(
                UserNotFoundException.class,
                () -> userController.getUser(1L)
        );

        assertThat(result.getMessage(), equalTo("No user was found with the given ID: #1."));
    }
}
