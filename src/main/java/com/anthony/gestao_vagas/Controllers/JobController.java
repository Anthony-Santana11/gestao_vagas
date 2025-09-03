package com.anthony.gestao_vagas.Controllers;

import com.anthony.gestao_vagas.Dto.CreateJobDTO;
import com.anthony.gestao_vagas.Entity.JobEntity;
import com.anthony.gestao_vagas.modules.candidate.useCases.CreateJobUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/company/job")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;



    @PostMapping("/")
    @PreAuthorize("hasRole('COMPANY')")
    @Tag(name = "Jobs", description = "Endpoints for Company to manage jobs")
    @Operation(summary = "Jobs management", description = "This endpoint allows Companies to create and manage jobs.")
    @ApiResponses(
            @ApiResponse (responseCode = "200", content = {
                    @Content (schema = @Schema(implementation = JobEntity.class))
            })
    )
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> create (@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {
        var companyId = request.getAttribute("company_id");

        try {
            var jobEntity =  JobEntity.builder()
                    .benefits(createJobDTO.getBenefits())
                    .companyId(UUID.fromString(companyId.toString()))
                    .description(createJobDTO.getDescription())
                    .level(createJobDTO.getLevel())
                    .build();

           var result = this.createJobUseCase.execute(jobEntity);
            ResponseEntity.ok().body(result);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error creating job: " + e.getMessage());
        }


    }
}
