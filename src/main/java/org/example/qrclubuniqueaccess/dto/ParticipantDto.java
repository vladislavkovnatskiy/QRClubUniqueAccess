package org.example.qrclubuniqueaccess.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantDto {
    @NotBlank(message = "Имя обязательно!")
    private String firstName;
    private String lastName;
    private String middleName;
}
