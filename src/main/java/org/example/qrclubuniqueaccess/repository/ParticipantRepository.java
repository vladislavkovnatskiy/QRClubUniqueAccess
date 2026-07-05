package org.example.qrclubuniqueaccess.repository;

import org.example.qrclubuniqueaccess.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
