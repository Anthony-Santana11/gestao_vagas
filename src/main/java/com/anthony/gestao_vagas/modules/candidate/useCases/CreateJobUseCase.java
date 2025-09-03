package com.anthony.gestao_vagas.modules.candidate.useCases;

import com.anthony.gestao_vagas.Entity.JobEntity;
import com.anthony.gestao_vagas.Exceptions.CompanyNotFoundException;
import com.anthony.gestao_vagas.Repository.CompanyRepository;
import com.anthony.gestao_vagas.Repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public JobEntity execute(JobEntity jobEntity) {
        companyRepository.findById(jobEntity.getCompanyId()).orElseThrow(() -> {
            throw new CompanyNotFoundException();
        });
        return this.jobRepository.save(jobEntity);
    }

}
