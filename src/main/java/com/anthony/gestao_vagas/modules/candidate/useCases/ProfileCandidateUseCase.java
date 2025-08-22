package com.anthony.gestao_vagas.modules.candidate.useCases;

import com.anthony.gestao_vagas.Dto.ProfileCandidateResponseDTO;
import com.anthony.gestao_vagas.Repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute (UUID idCandidate) {
        var candidate = this.candidateRepository.findById(idCandidate)
                .orElseThrow(() -> {
                throw new RuntimeException("Candidate not found");
                });

        var candidateDTO = ProfileCandidateResponseDTO.builder()
                .description(candidate.getDescription())
                .username(candidate.getUsername())
                .email(candidate.getEmail())
                .phone_number(candidate.getPhoneNumber())
                .name(candidate.getName())
                .id(candidate.getId())
                .build();

        return candidateDTO;
    }

}
