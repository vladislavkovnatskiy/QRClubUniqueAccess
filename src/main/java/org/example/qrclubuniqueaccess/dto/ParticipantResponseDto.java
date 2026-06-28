package org.example.qrclubuniqueaccess.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String qrUuid;
}
