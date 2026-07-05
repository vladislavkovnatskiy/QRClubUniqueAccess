package org.example.qrclubuniqueaccess.controller;

import lombok.RequiredArgsConstructor;
import org.example.qrclubuniqueaccess.dto.ScanResponseDto;
import org.example.qrclubuniqueaccess.service.QrService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/qr")
@RequiredArgsConstructor
public class QrScanController {

    private final QrService qrService;

    @GetMapping("/scan/{uuid}")
    public ResponseEntity<ScanResponseDto> scanQr(@PathVariable UUID uuid) {
        ScanResponseDto response = qrService.scanQrCode(uuid);
        return ResponseEntity.ok(response);
    }
}
