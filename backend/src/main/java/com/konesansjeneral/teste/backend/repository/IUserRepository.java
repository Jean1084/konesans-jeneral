package com.konesansjeneral.teste.backend.repository;

import com.konesansjeneral.teste.backend.exception.UserNotFoundException;
import com.konesansjeneral.teste.backend.model.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

import static com.konesansjeneral.teste.backend.security.TokenService.JWT_EMAIL_CLAIM_NAME;
import static com.konesansjeneral.teste.backend.security.TokenService.JWT_PROVIDER_CLAIM_NAME;


@Repository
public interface IUserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmailAndProvider(String email, String provider);

    default UserEntity findByAccessToken(OAuth2AuthenticationToken token) {
        var email = (String) token.getPrincipal().getAttribute(JWT_EMAIL_CLAIM_NAME);
        var provider = (String) token.getPrincipal().getAttribute(JWT_PROVIDER_CLAIM_NAME);
        return findByEmailAndProvider(email, provider).orElseThrow(() -> new UserNotFoundException(email, provider));
    }
    Optional<UserEntity> findByRefreshToken(String refreshToken);

}