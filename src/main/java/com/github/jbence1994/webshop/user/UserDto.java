package com.github.jbence1994.webshop.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;
    private String email;
    private ProfileDto profile;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
