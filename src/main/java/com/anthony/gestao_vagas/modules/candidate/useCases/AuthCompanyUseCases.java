package com.anthony.gestao_vagas.modules.candidate.useCases;

import com.anthony.gestao_vagas.modules.candidate.Dto.AuthCompanyDTO;
import com.anthony.gestao_vagas.modules.candidate.Repository.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import java.time.Duration;
import java.time.Instant;

@Service
public class AuthCompanyUseCases {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public String execute (AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
                () -> new RuntimeException("Username/company not found"));

       var passwordMatches =  this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

       // if it's wrong.
        if (!passwordMatches) {
            throw new AuthenticationException();
        }

       // if it's correct, generate a token
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create().withIssuer("gestao-vagas")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withSubject(company.getId().toString())
                .sign(algorithm);
        return token;
    }


}
