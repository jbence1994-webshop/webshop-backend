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
@Table(name = "recovery_codes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RecoveryCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @OneToOne
    @JoinColumn(name = "user_id")
    private EncryptedUser user;

    private LocalDateTime expirationDate;

    public RecoveryCode(String code, EncryptedUser user) {
        this.code = code;
        this.user = user;
        this.expirationDate = LocalDateTime.now().plusMinutes(10);
    }

    public boolean isExpired() {
        return expirationDate.isBefore(LocalDateTime.now());
    }
}
