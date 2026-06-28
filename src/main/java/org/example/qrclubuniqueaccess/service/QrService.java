package org.example.qrclubuniqueaccess.service;

import org.springframework.transaction.annotation.Transactional;
import org.example.qrclubuniqueaccess.dto.ParticipantDto;
import org.example.qrclubuniqueaccess.dto.ScanResponseDto;
import org.example.qrclubuniqueaccess.entity.Participant;
import org.example.qrclubuniqueaccess.entity.QrCode;
import org.example.qrclubuniqueaccess.repository.ParticipantRepository;
import org.example.qrclubuniqueaccess.repository.QrCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class QrService {

    @Autowired
    private QrCodeRepository qrCodeRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    // Сканирование QR-кода – меняем UUID на новый
    @Transactional
    public ScanResponseDto scanQrCode(UUID qrUuid){
        QrCode qrCode = qrCodeRepository.findByQrUuid(qrUuid).orElseThrow(() -> new RuntimeException("QR-код не найден или уже использован"));

        Participant participant = qrCode.getParticipant();

        Long participantId = participant.getId();
        String fullName = participant.getLastName() + " " + participant.getFirstName() + " "
                + (participant.getMiddleName() != null ? " " + participant.getMiddleName() : " ");

        UUID newUuid = UUID.randomUUID();
        qrCode.setQrUuid(newUuid);
        qrCodeRepository.save(qrCode);

        return new ScanResponseDto(participantId, fullName, newUuid.toString());
    }

    // Создание участника с автоматической генерацией QR-кода
    public Participant createParticipant(ParticipantDto dto){
        Participant participant = new Participant(dto.getFirstName(), dto.getLastName(), dto.getMiddleName());
        QrCode qrCode = new QrCode(participant, UUID.randomUUID());
        participant.setQrCode(qrCode);
        return participantRepository.save(participant);
    }

    // Обновление данных участника (QR-код не меняем)
    public Participant updateParticipant(Long id, ParticipantDto dto){
        Participant participant = participantRepository.findById(id).orElseThrow(()-> new RuntimeException("Участник не найден"));
        participant.setFirstName(dto.getFirstName());
        participant.setLastName(dto.getLastName());
        participant.setMiddleName(dto.getMiddleName());
        return participantRepository.save(participant);
    }

    // Удаление участника (QR-код удалится каскадно из-за orphanRemoval)
    public void deleteParticipant(long id){
        participantRepository.deleteById(id);;
    }

    // Принудительная регенерация QR-кода для участника - сервисная функция (нет в ТЗ)
    @Transactional
    public String regenerateQrCode(Long participantId){
        Participant participant = participantRepository.findById(participantId).orElseThrow(()-> new RuntimeException("Участник не найден"));
        QrCode qrCode = participant.getQrCode();
        if (qrCode == null){
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
