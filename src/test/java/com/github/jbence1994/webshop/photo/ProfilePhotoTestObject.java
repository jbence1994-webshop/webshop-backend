package com.github.jbence1994.webshop.photo;

import static com.github.jbence1994.webshop.photo.PhotoTestConstants.PHOTO_FILE_NAME;
import static com.github.jbence1994.webshop.user.ProfileTestObject.profile;

public final class ProfilePhotoTestObject {
    public static ProfilePhoto profilePhoto() {
        return new ProfilePhoto(
                1L,
                profile(),
                PHOTO_FILE_NAME
        );
    }
}
