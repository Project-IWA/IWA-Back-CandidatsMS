package com.iwa.candidats.exception;

public class CandidatDeletionException extends RuntimeException {
    public CandidatDeletionException(String email) {
        super("Could not delete candidat with email: " + email);
    }
}
