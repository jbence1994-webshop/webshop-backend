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
import static com.github.jbence1994.webshop.user.RegistrationRequestTestObject.notSanitizedRegistrationRequest;
import static com.github.jbence1994.webshop.user.RegistrationRequestTestObject.notSanitizedRegistrationRequestWithNullMiddleName;
import static com.github.jbence1994.webshop.user.UserTestConstants.CONFIRM_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestConstants.EMAIL_1;
import static com.github.jbence1994.webshop.user.UserTestConstants.FIRST_NAME;
import static com.github.jbence1994.webshop.user.UserTestConstants.LAST_NAME;
import static com.github.jbence1994.webshop.user.UserTestConstants.MIDDLE_NAME;
import static com.github.jbence1994.webshop.user.UserTestConstants.PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestConstants.PHONE_NUMBER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

@ExtendWith(MockitoExtension.class)
public class RegistrationRequestSanitizerTests {

    @InjectMocks
    private RegistrationRequestSanitizer registrationRequestSanitizer;

    @Test
    public void sanitizeTest_1() {
        var result = registrationRequestSanitizer.sanitize(notSanitizedRegistrationRequest());

        assertThat(result.user().email(), equalTo(EMAIL_1));
        assertThat(result.user().password(), equalTo(PASSWORD));
        assertThat(result.user().confirmPassword(), equalTo(CONFIRM_PASSWORD));
        assertThat(result.user().firstName(), equalTo(FIRST_NAME));
        assertThat(result.user().middleName(), equalTo(MIDDLE_NAME));
        assertThat(result.user().lastName(), equalTo(LAST_NAME));
        assertThat(result.user().phoneNumber(), equalTo(PHONE_NUMBER));

        assertThat(result.user().address().addressLine(), equalTo(ADDRESS_LINE));
        assertThat(result.user().address().municipality(), equalTo(MUNICIPALITY));
        assertThat(result.user().address().province(), equalTo(PROVINCE));
        assertThat(result.user().address().postalCode(), equalTo(POSTAL_CODE));
        assertThat(result.user().address().country(), equalTo(COUNTRY));
    }

    @Test
    public void sanitizeTest_2() {
        var result = registrationRequestSanitizer.sanitize(notSanitizedRegistrationRequestWithNullMiddleName());

        assertThat(result.user().email(), equalTo(EMAIL_1));
        assertThat(result.user().password(), equalTo(PASSWORD));
        assertThat(result.user().confirmPassword(), equalTo(CONFIRM_PASSWORD));
        assertThat(result.user().firstName(), equalTo(FIRST_NAME));
        assertThat(result.user().middleName(), is(nullValue()));
        assertThat(result.user().lastName(), equalTo(LAST_NAME));
        assertThat(result.user().phoneNumber(), equalTo(PHONE_NUMBER));

        assertThat(result.user().address().addressLine(), equalTo(ADDRESS_LINE));
        assertThat(result.user().address().municipality(), equalTo(MUNICIPALITY));
        assertThat(result.user().address().province(), equalTo(PROVINCE));
        assertThat(result.user().address().postalCode(), equalTo(POSTAL_CODE));
        assertThat(result.user().address().country(), equalTo(COUNTRY));
    }
}
