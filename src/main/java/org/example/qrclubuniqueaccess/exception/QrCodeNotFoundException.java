package org.example.qrclubuniqueaccess.exception;

public class QrCodeNotFoundException extends RuntimeException {
    public QrCodeNotFoundException() {
        super("QR-код не найден или уже использован");
    }
}
