package org.example.qrclubuniqueaccess.mapper;

import org.example.qrclubuniqueaccess.dto.ParticipantDto;
import org.example.qrclubuniqueaccess.dto.ParticipantResponseDto;
import org.example.qrclubuniqueaccess.dto.ScanResponseDto;
import org.example.qrclubuniqueaccess.entity.Participant;
import org.springframework.stereotype.Component;

@Component
public class ParticipantMapper {

    public ParticipantResponseDto toResponseDto(Participant participant) {
        if (participant == null) {
            return null;
        }
        String qrUuid = participant.getQrCode() != null
                ? participant.getQrCode().getQrUuid().toString()
                : null;
        return new ParticipantResponseDto(
                participant.getId(),
                participant.getFirstName(),
                participant.getLastName(),
                participant.getMiddleName(),
                qrUuid
        );
    }

    public Participant toEntity(ParticipantDto dto) {
        return new Participant(dto.firstName(), dto.lastName(), dto.middleName());
    }

    public ScanResponseDto toScanResponseDto(Participant participant, String newUuid) {
        String fullName = participant.getLastName() + " " + participant.getFirstName() + " "
                + (participant.getMiddleName() != null ? " " + participant.getMiddleName() : " ");
        return new ScanResponseDto(participant.getId(), fullName, newUuid);
    }

    public void updateEntity(Participant participant, ParticipantDto dto) {
        participant.setFirstName(dto.firstName());
        participant.setLastName(dto.lastName());
        participant.setMiddleName(dto.middleName());
    }
}
