package org.example.qrclubuniqueaccess.castomexception;

public class ParticipantNotFoundException extends RuntimeException {
    public ParticipantNotFoundException() {
        super("Участник не найден");
    }
}
