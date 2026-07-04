package org.example.qrclubuniqueaccess.service;

import lombok.RequiredArgsConstructor;
import org.example.qrclubuniqueaccess.castomexception.ParticipantNotFoundException;
import org.example.qrclubuniqueaccess.castomexception.QrCodeNotFoundException;
import org.example.qrclubuniqueaccess.dto.ParticipantResponseDto;
import org.example.qrclubuniqueaccess.mapper.ParticipantMapper;
import org.springframework.transaction.annotation.Transactional;
import org.example.qrclubuniqueaccess.dto.ParticipantDto;
import org.example.qrclubuniqueaccess.dto.ScanResponseDto;
import org.example.qrclubuniqueaccess.entity.Participant;
import org.example.qrclubuniqueaccess.entity.QrCode;
import org.example.qrclubuniqueaccess.repository.ParticipantRepository;
import org.example.qrclubuniqueaccess.repository.QrCodeRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QrService {

    private final QrCodeRepository qrCodeRepository;
    private final ParticipantRepository participantRepository;
    private final ParticipantMapper participantMapper;


    // Сканирование QR-кода – меняем UUID на новый
    @Transactional
    public ScanResponseDto scanQrCode(UUID qrUuid) {
        QrCode qrCode = qrCodeRepository.findByQrUuid(qrUuid)
                .orElseThrow(QrCodeNotFoundException::new);

        Participant participant = qrCode.getParticipant();

        UUID newUuid = UUID.randomUUID();
        qrCode.setQrUuid(newUuid);
        qrCodeRepository.save(qrCode);

        return participantMapper.toScanResponseDto(participant, newUuid.toString());
    }

    // Создание участника с автоматической генерацией QR-кода
    public ParticipantResponseDto createParticipant(ParticipantDto dto) {
        Participant participant = participantMapper.toEntity(dto);
        QrCode qrCode = new QrCode(participant, UUID.randomUUID());
        participant.setQrCode(qrCode);
        Participant saved = participantRepository.save(participant);
        return participantMapper.toResponseDto(saved);
    }

    // Обновление данных участника (QR-код не меняем)
    public ParticipantResponseDto updateParticipant(Long id, ParticipantDto dto) {
        Participant participant = participantRepository.findById(id)
                .orElseThrow(ParticipantNotFoundException::new);
        participantMapper.updateEntity(participant, dto);
        Participant update = participantRepository.save(participant);
        return participantMapper.toResponseDto(update);
    }

    // Удаление участника (QR-код удалится каскадно из-за orphanRemoval)
    public void deleteParticipant(long id) {
        participantRepository.deleteById(id);
    }

    // Принудительная регенерация QR-кода для участника - сервисная функция (нет в ТЗ)
    @Transactional
    public String regenerateQrCode(Long participantId) {
        Participant participant = participantRepository.findById(participantId)
                .orElseThrow(ParticipantNotFoundException::new);
        QrCode qrCode = participant.getQrCode();
        if (qrCode == null) {
            qrCode = new QrCode(participant, UUID.randomUUID());
            participant.setQrCode(qrCode);
        } else {
            UUID newUuid = UUID.randomUUID();
            qrCode.setQrUuid(newUuid);
        }
        participantRepository.save(participant);

        return participant.getQrCode().getQrUuid().toString();
    }
}
