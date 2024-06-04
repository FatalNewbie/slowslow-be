package com.elice.slowslow.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(name = "member_id", nullable = false, length = 20)
    private String memberId;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(name = "phone_number", nullable = false, length = 50)
    @Pattern(regexp = "\\d+", message = "전화번호는 숫자 형식으로 입력해주세요.")
    private String phoneNumber;

    @Column(nullable = false, length = 50)
    @Email(message = "이메일 형식이 유효하지 않습니다.")
    private String email;

    @Column(name = "deleted")
    private boolean deleted;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    public enum RoleType {
        ADMIN, USER
    }
}