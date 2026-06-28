package org.example.qrclubuniqueaccess.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "qr_codes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QrCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_id", unique = true, nullable = false)
    private Participant participant;

    @Column(name = "qr_uuid", nullable = false, unique = true)
    private UUID qrUuid;

    public QrCode(Participant participant, UUID qrUuid){
        this.participant = participant;
        this.qrUuid = qrUuid;
    }
}
