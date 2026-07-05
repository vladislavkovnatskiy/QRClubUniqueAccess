package org.example.qrclubuniqueaccess.repository;

import org.example.qrclubuniqueaccess.entity.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface QrCodeRepository extends JpaRepository<QrCode, Long> {
    Optional<QrCode> findByQrUuid(UUID qrUuid);
}
