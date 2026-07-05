package org.example.qrclubuniqueaccess.dto;


public record ParticipantResponseDto(
        Long id,
        String firstName,
        String lastName,
        String middleName,
        String qrUuid
) {}
