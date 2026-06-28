package org.example.qrclubuniqueaccess.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScanResponseDto {
    private Long participantId;
    private String fullName;
    private String newQrUuid;
}
