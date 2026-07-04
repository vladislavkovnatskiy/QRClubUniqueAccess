package org.example.qrclubuniqueaccess.castomexception;

public class QrCodeNotFoundException extends RuntimeException {
    public QrCodeNotFoundException() {
        super("QR-код не найден или уже использован");
    }
}
