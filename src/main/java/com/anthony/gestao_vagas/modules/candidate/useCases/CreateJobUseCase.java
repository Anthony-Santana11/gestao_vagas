package com.anthony.gestao_vagas.modules.candidate.useCases;

import com.anthony.gestao_vagas.modules.candidate.Entity.JobEntity;
import com.anthony.gestao_vagas.modules.candidate.Repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;
    public JobEntity execute(JobEntity jobEntity) {
       return this.jobRepository.save(jobEntity);
    }

}
