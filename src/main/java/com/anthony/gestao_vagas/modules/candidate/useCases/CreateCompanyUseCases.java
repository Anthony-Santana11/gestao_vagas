package com.anthony.gestao_vagas.modules.candidate.useCases;

import com.anthony.gestao_vagas.Entity.CompanyEntity;
import com.anthony.gestao_vagas.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class CreateCompanyUseCases {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEnconder;
    public CompanyEntity execute (CompanyEntity companyEntity) {

        this.companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
        .ifPresent((user) -> {
            throw new RuntimeException("Username ou email já cadastrado");
        });

        var password = passwordEnconder.encode(companyEntity.getPassword());
        companyEntity.setPassword(password);

       return this.companyRepository.save(companyEntity);
    }
}
