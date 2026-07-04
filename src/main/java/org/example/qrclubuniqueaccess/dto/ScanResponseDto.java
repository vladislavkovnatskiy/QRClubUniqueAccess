package org.example.qrclubuniqueaccess.dto;

public record ScanResponseDto(
        Long participantId,
        String fullName,
        String newQrUuid
) {}
