package com.github.jbence1994.webshop.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "temporary_passwords")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TemporaryPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;

    @OneToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private LocalDateTime expirationDate;

    public TemporaryPassword(String password, User user, LocalDateTime expirationDate) {
        this.password = password;
        this.user = user;
        this.expirationDate = expirationDate;
    }

    public boolean isExpired() {
        return expirationDate.isBefore(LocalDateTime.now());
    }
}
