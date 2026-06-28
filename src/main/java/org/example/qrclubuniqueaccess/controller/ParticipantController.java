package org.example.qrclubuniqueaccess.controller;

import jakarta.validation.Valid;
import org.example.qrclubuniqueaccess.dto.ParticipantDto;
import org.example.qrclubuniqueaccess.dto.ParticipantResponseDto;
import org.example.qrclubuniqueaccess.dto.QrRegenerateResponseDto;
import org.example.qrclubuniqueaccess.entity.Participant;
import org.example.qrclubuniqueaccess.service.QrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {

    @Autowired
    private QrService qrService;

    // мапим сущности в DTO
    private ParticipantResponseDto toResponseDto(Participant participant){
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

    @PostMapping
    public ResponseEntity<ParticipantResponseDto> createParticipant(@Valid @RequestBody ParticipantDto dto){
        Participant created = qrService.createParticipant(dto);
        return new ResponseEntity<>(toResponseDto(created), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipantResponseDto> updateParticipant(@PathVariable Long id, @RequestBody ParticipantDto dto){
        Participant update = qrService.updateParticipant(id, dto);
        return ResponseEntity.ok(toResponseDto(update));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Valid> deleteParticipant(@PathVariable Long id){
        qrService.deleteParticipant(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/regenerate_qr")
    public ResponseEntity<QrRegenerateResponseDto> regenerateQr(@PathVariable Long id){
        String newQr = qrService.regenerateQrCode(id);
        return ResponseEntity.ok(new QrRegenerateResponseDto(newQr));
    }
}
