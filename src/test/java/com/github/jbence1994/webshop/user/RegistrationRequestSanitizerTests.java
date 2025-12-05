package com.github.jbence1994.webshop.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.user.DecryptedAddressTestConstants.DECRYPTED_ADDRESS_LINE;
import static com.github.jbence1994.webshop.user.DecryptedAddressTestConstants.DECRYPTED_COUNTRY;
import static com.github.jbence1994.webshop.user.DecryptedAddressTestConstants.DECRYPTED_MUNICIPALITY;
import static com.github.jbence1994.webshop.user.DecryptedAddressTestConstants.DECRYPTED_POSTAL_CODE;
import static com.github.jbence1994.webshop.user.DecryptedAddressTestConstants.DECRYPTED_PROVINCE;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.DECRYPTED_EMAIL_1;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.DECRYPTED_FIRST_NAME;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.DECRYPTED_LAST_NAME;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.DECRYPTED_MIDDLE_NAME;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.DECRYPTED_PHONE_NUMBER;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.RAW_CONFIRM_PASSWORD;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.RAW_PASSWORD;
import static com.github.jbence1994.webshop.user.RegistrationRequestTestObject.notSanitizedRegistrationRequest;
import static com.github.jbence1994.webshop.user.RegistrationRequestTestObject.notSanitizedRegistrationRequestWithNullMiddleName;
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

        assertThat(result.user().email(), equalTo(DECRYPTED_EMAIL_1));
        assertThat(result.user().password(), equalTo(RAW_PASSWORD));
        assertThat(result.user().confirmPassword(), equalTo(RAW_CONFIRM_PASSWORD));
        assertThat(result.user().firstName(), equalTo(DECRYPTED_FIRST_NAME));
        assertThat(result.user().middleName(), equalTo(DECRYPTED_MIDDLE_NAME));
        assertThat(result.user().lastName(), equalTo(DECRYPTED_LAST_NAME));
        assertThat(result.user().phoneNumber(), equalTo(DECRYPTED_PHONE_NUMBER));

        assertThat(result.user().address().addressLine(), equalTo(DECRYPTED_ADDRESS_LINE));
        assertThat(result.user().address().municipality(), equalTo(DECRYPTED_MUNICIPALITY));
        assertThat(result.user().address().province(), equalTo(DECRYPTED_PROVINCE));
        assertThat(result.user().address().postalCode(), equalTo(DECRYPTED_POSTAL_CODE));
        assertThat(result.user().address().country(), equalTo(DECRYPTED_COUNTRY));
    }

    @Test
    public void sanitizeTest_2() {
        var result = registrationRequestSanitizer.sanitize(notSanitizedRegistrationRequestWithNullMiddleName());

        assertThat(result.user().email(), equalTo(DECRYPTED_EMAIL_1));
        assertThat(result.user().password(), equalTo(RAW_PASSWORD));
        assertThat(result.user().confirmPassword(), equalTo(RAW_CONFIRM_PASSWORD));
        assertThat(result.user().firstName(), equalTo(DECRYPTED_FIRST_NAME));
        assertThat(result.user().middleName(), is(nullValue()));
        assertThat(result.user().lastName(), equalTo(DECRYPTED_LAST_NAME));
        assertThat(result.user().phoneNumber(), equalTo(DECRYPTED_PHONE_NUMBER));

        assertThat(result.user().address().addressLine(), equalTo(DECRYPTED_ADDRESS_LINE));
        assertThat(result.user().address().municipality(), equalTo(DECRYPTED_MUNICIPALITY));
        assertThat(result.user().address().province(), equalTo(DECRYPTED_PROVINCE));
        assertThat(result.user().address().postalCode(), equalTo(DECRYPTED_POSTAL_CODE));
        assertThat(result.user().address().country(), equalTo(DECRYPTED_COUNTRY));
    }
}
