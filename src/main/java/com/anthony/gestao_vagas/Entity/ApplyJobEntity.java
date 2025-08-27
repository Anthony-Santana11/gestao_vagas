package com.anthony.gestao_vagas.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "apply_job")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplyJobEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "candidate_id", insertable = false, updatable = false)
     private CandidateEntity candidateEntity;

    @ManyToOne
    @JoinColumn(name = "job_id", insertable = false, updatable = false)
     private JobEntity jobEntity;

    @Column(name = "candidate_id")
     private UUID idCandidate;

    @Column(name = "job_id")
     private UUID idJob;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
