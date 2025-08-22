package com.anthony.gestao_vagas.modules.candidate.useCases;

import com.anthony.gestao_vagas.Entity.JobEntity;
import com.anthony.gestao_vagas.Repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllJobsByFilterUseCase {


    @Autowired
    private JobRepository jobRepository;

    public List<JobEntity> execute (String description) {

      return this.jobRepository.findByDescriptionContainingIgnoreCase(description);

    }
}
