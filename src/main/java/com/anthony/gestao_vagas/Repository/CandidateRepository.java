package com.anthony.gestao_vagas.Repository;

import  com.anthony.gestao_vagas.Entity.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository <CandidateEntity, UUID> {
      Optional<CandidateEntity> findByUsernameOrEmail (String username , String email);
      Optional<CandidateEntity> findByUsername(String username);

}
