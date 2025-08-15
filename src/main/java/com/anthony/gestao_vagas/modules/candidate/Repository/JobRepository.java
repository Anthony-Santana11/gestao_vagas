package com.anthony.gestao_vagas.modules.candidate.Repository;

import com.anthony.gestao_vagas.modules.candidate.Entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {

}
