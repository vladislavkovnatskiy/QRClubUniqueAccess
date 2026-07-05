package org.example.qrclubuniqueaccess.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.qrclubuniqueaccess.dto.ParticipantDto;
import org.example.qrclubuniqueaccess.dto.ParticipantResponseDto;
import org.example.qrclubuniqueaccess.dto.QrRegenerateResponseDto;
import org.example.qrclubuniqueaccess.service.QrService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/participants")
@RequiredArgsConstructor
public class ParticipantController {

    private final QrService qrService;

    @PostMapping
    public ResponseEntity<ParticipantResponseDto> createParticipant(@Valid @RequestBody ParticipantDto dto) {
        ParticipantResponseDto created = qrService.createParticipant(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipantResponseDto> updateParticipant(@PathVariable Long id, @RequestBody ParticipantDto dto) {
        ParticipantResponseDto update = qrService.updateParticipant(id, dto);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Valid> deleteParticipant(@PathVariable Long id) {
        qrService.deleteParticipant(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/regenerate_qr")
    public ResponseEntity<QrRegenerateResponseDto> regenerateQr(@PathVariable Long id) {
        String newQr = qrService.regenerateQrCode(id);
        return ResponseEntity.ok(new QrRegenerateResponseDto(newQr));
    }
}
