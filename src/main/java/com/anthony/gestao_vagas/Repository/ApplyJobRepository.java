package com.anthony.gestao_vagas.Repository;

import com.anthony.gestao_vagas.Entity.ApplyJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyJobRepository extends JpaRepository <ApplyJobEntity, java.util.UUID> {
}
