package com.konesansjeneral.teste.backend.exception;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(String email, String provider) {
        super("Utilisateur avec l'email '%s' et le provider '%s' non trouvé".formatted(email, provider));
    }
}

