package com.anthony.gestao_vagas.Entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "job")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;
    @Schema(example = "Vaga para Desenvolvedor Java Junior")
    private String description;

    @NotBlank(message = "Esse campo é Obrigatório")
    @Schema(example = "JUNIOR")
    private String level;

    @NotBlank(message = "Esse campo é Obrigatório")
    @Schema(example = "Gym Pass, Plano de saúde, Vale Refeição")
    private String benefits;

    @ManyToOne
    @JoinColumn(name = "company_id", insertable = false, updatable = false, nullable = false)
    private CompanyEntity companyEntity;

    @Column(name = "company_id")
    private UUID companyId;

    @CreationTimestamp
    private LocalDateTime CreatedAt;
}
