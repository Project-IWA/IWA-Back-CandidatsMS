package com.iwa.candidats.exception;

public class CandidatNotFoundException extends RuntimeException {
    public CandidatNotFoundException(String email) {
        super("Candidat not found with email: " + email);
    }
}
