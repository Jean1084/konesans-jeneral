package com.konesansjeneral.teste.backend.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.konesansjeneral.teste.backend.model.response.QuizPlayApiResponse;
import com.konesansjeneral.teste.backend.model.response.UserApiResponse;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "T_USER")
public class UserEntity {

    public static final Integer JWT_REFRESH_TOKEN_DURATION_IN_MILLIS = 24 * 60 * 60 * 1000; // 24 hours

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String email;

    private String provider;

    private String name;

    private String image;

    @Column(name = "REFRESH_TOKEN")
    private String refreshToken;

    @Column(name = "REFRESH_TOKEN_CREATED_AT")
    private ZonedDateTime refreshTokenCreatedAt;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    private List<QuizPlayEntity> quizPlays = new ArrayList<>();

    public UserApiResponse toApiResponse() {
        var res = new UserApiResponse();
        res.setId(getId());
        res.setName(getName());
        res.setEmail(getEmail());
        res.setProvider(getProvider());
        res.setImage(getImage());
        return res;
    }

    public List<QuizPlayApiResponse> toQuizPlaysApiResponse() {
        return getQuizPlays().stream().map(QuizPlayEntity::toApiResponse).toList();
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        this.refreshTokenCreatedAt = ZonedDateTime.now();
    }

    public boolean isRefreshTokenExpired() {
        return refreshTokenCreatedAt.toInstant().plusMillis(JWT_REFRESH_TOKEN_DURATION_IN_MILLIS).isBefore(ZonedDateTime.now().toInstant());
    }

}