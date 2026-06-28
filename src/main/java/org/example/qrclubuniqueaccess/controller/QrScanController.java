package org.example.qrclubuniqueaccess.controller;

import org.example.qrclubuniqueaccess.dto.ScanResponseDto;
import org.example.qrclubuniqueaccess.service.QrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/qr")
public class QrScanController {

    @Autowired
    private QrService qrService;

    @GetMapping("/scan/{uuid}")
    public ResponseEntity<ScanResponseDto> scanQr(@PathVariable UUID uuid){
        ScanResponseDto response = qrService.scanQrCode(uuid);
        return ResponseEntity.ok(response);
    }
}
