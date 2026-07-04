package org.example.qrclubuniqueaccess.dto;

import jakarta.validation.constraints.NotBlank;

public record ParticipantDto(
        @NotBlank(message = "Имя обязательно!")
        String firstName,
        String lastName,
        String middleName
) {}
