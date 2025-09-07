package com.github.jbence1994.webshop.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.user.AddressTestConstants.ADDRESS_LINE;
import static com.github.jbence1994.webshop.user.AddressTestConstants.COUNTRY;
import static com.github.jbence1994.webshop.user.AddressTestConstants.MUNICIPALITY;
import static com.github.jbence1994.webshop.user.AddressTestConstants.POSTAL_CODE;
import static com.github.jbence1994.webshop.user.AddressTestConstants.PROVINCE;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.FIRST_NAME;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.LAST_NAME;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.MIDDLE_NAME;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.PHONE_NUMBER;
import static com.github.jbence1994.webshop.user.RegistrationRequestTestObject.notSanitizedRegistrationRequest;
import static com.github.jbence1994.webshop.user.UserTestConstants.CONFIRM_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestConstants.EMAIL;
import static com.github.jbence1994.webshop.user.UserTestConstants.PASSWORD;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(MockitoExtension.class)
public class RegistrationRequestSanitizerTests {

    @InjectMocks
    private RegistrationRequestSanitizer registrationRequestSanitizer;

    @Test
    public void sanitizeTest() {
        var result = registrationRequestSanitizer.sanitize(notSanitizedRegistrationRequest());

        assertThat(result.getUser().getEmail(), equalTo(EMAIL));
        assertThat(result.getUser().getPassword(), equalTo(PASSWORD));
        assertThat(result.getUser().getConfirmPassword(), equalTo(CONFIRM_PASSWORD));

        assertThat(result.getProfile().getFirstName(), equalTo(FIRST_NAME));
        assertThat(result.getProfile().getMiddleName(), equalTo(MIDDLE_NAME));
        assertThat(result.getProfile().getLastName(), equalTo(LAST_NAME));
        assertThat(result.getProfile().getPhoneNumber(), equalTo(PHONE_NUMBER));

        assertThat(result.getAddress().getAddressLine(), equalTo(ADDRESS_LINE));
        assertThat(result.getAddress().getMunicipality(), equalTo(MUNICIPALITY));
        assertThat(result.getAddress().getProvince(), equalTo(PROVINCE));
        assertThat(result.getAddress().getPostalCode(), equalTo(POSTAL_CODE));
        assertThat(result.getAddress().getCountry(), equalTo(COUNTRY));
    }
}
