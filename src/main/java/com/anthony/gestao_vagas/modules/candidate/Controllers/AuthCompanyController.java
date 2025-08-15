package com.anthony.gestao_vagas.modules.candidate.Controllers;


import com.anthony.gestao_vagas.modules.candidate.Dto.AuthCompanyDTO;
import com.anthony.gestao_vagas.modules.candidate.useCases.AuthCompanyUseCases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthCompanyController {
    @Autowired
    private AuthCompanyUseCases authCompanyUseCases;

    @PostMapping("/company")
    public ResponseEntity<Object> create (@RequestBody AuthCompanyDTO authCompanyDTO) {
        try {
            var result = this.authCompanyUseCases.execute(authCompanyDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
