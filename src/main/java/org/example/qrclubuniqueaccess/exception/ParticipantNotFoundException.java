package org.example.qrclubuniqueaccess.exception;

public class ParticipantNotFoundException extends RuntimeException {
    public ParticipantNotFoundException() {
        super("Участник не найден");
    }
}
