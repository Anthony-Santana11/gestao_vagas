package com.anthony.gestao_vagas.modules.candidate.useCases;

import com.anthony.gestao_vagas.Dto.AuthCompanyDTO;
import com.anthony.gestao_vagas.Dto.AuthCompanyResponseDTO;
import com.anthony.gestao_vagas.Repository.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.security.sasl.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCompanyUseCases {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public AuthCompanyResponseDTO execute (AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
                () -> new RuntimeException("Username/company not found"));

       var passwordMatches =  this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

       // if it's wrong.
        if (!passwordMatches) {
            throw new AuthenticationException();
        }

       // if it's correct, generate a token
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create().withIssuer("gestao-vagas")
                .withClaim("roles", Arrays.asList("COMPANY"))
                .withExpiresAt(expiresIn)
                .withSubject(company.getId().toString())
                .sign(algorithm);

       var authCompanyResponseDTO =  AuthCompanyResponseDTO.builder()
                .access_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();

        return authCompanyResponseDTO;
    }


}
