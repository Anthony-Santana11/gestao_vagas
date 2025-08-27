package com.anthony.gestao_vagas.modules.candidate.useCases;


import com.anthony.gestao_vagas.Exceptions.JobNotFoundException;
import com.anthony.gestao_vagas.Exceptions.UserNotFoundException;
import com.anthony.gestao_vagas.Repository.ApplyJobRepository;
import com.anthony.gestao_vagas.Repository.CandidateRepository;
import com.anthony.gestao_vagas.Repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public void execute (UUID idCandidate , UUID idJob) {
        this.candidateRepository.findById(idCandidate)
                .orElseThrow( () -> {
                    throw new UserNotFoundException();
                });

        this.jobRepository.findById(idJob)
            .orElseThrow( () -> {
                throw new JobNotFoundException();
            });

    }

}
