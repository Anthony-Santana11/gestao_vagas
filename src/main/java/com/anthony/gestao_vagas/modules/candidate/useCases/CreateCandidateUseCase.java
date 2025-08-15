package com.anthony.gestao_vagas.modules.candidate.useCases;

import com.anthony.gestao_vagas.modules.candidate.Entity.CandidateEntity;
import com.anthony.gestao_vagas.modules.candidate.Repository.CandidateRepository;
import com.anthony.gestao_vagas.modules.candidate.Exceptions.UserFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

   public  CandidateEntity execute(CandidateEntity candidateEntity) {
       this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername() , candidateEntity.getEmail())
               .ifPresent(user -> {
                   throw new UserFoundException();
               });

     var password = passwordEncoder.encode(candidateEntity.getPassword());
        candidateEntity.setPassword(password);

       return this.candidateRepository.save(candidateEntity);

   }
}
