package com.anthony.gestao_vagas.Controllers;


import com.anthony.gestao_vagas.Dto.ProfileCandidateResponseDTO;
import com.anthony.gestao_vagas.Entity.ApplyJobEntity;
import com.anthony.gestao_vagas.Entity.CandidateEntity;
import com.anthony.gestao_vagas.Entity.JobEntity;
import com.anthony.gestao_vagas.modules.candidate.useCases.ApplyJobCandidateUseCase;
import com.anthony.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import com.anthony.gestao_vagas.modules.candidate.useCases.ListAllJobsByFilterUseCase;
import com.anthony.gestao_vagas.modules.candidate.useCases.ProfileCandidateUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.TableGenerator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.SecurityMarker;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidate", description = "Endpoints for Candidate operations")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @Autowired
    private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

    @Autowired
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @PostMapping("/")
    @Operation (summary = "Candidates Register ", description = "This endpoint allows a candidate to register in the system. " + "It requires a valid candidate entity with all necessary fields filled out.")
    @ApiResponses({
            @ApiResponse(responseCode = "200" , content = {
                    @Content(schema = @Schema(implementation = ProfileCandidateResponseDTO.class))
            } ),
            @ApiResponse(responseCode = "400" , description = "User already exists or invalid data provided")
    })
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
        try {
        var result =  this.createCandidateUseCase.execute(candidateEntity);
        return ResponseEntity.ok().body(result);


    } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('CANDIDATE')")
    @GetMapping("/")
    @Operation(summary = "Get Candidate Profile", description = "This endpoint retrieves the profile of the authenticated candidate. " + "It requires a valid JWT token in the Authorization header.")
    @ApiResponses({
            @ApiResponse(responseCode = "200" , content = {
                    @Content(schema = @Schema(implementation = ProfileCandidateResponseDTO.class))
            } ),
            @ApiResponse(responseCode = "400" , description = "User not found or invalid request")
    })
    public ResponseEntity<Object> get(HttpServletRequest request) {
        var idCandidate = request.getAttribute("candidate_id");
        try {
            var profile = this.profileCandidateUseCase.execute(UUID.fromString(idCandidate.toString()));
            return ResponseEntity.ok().body(profile);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/job")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation (summary = "List all jobs by filter", description = "This endpoint allows candidates to search for jobs based on a description filter.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = JobEntity.class)))
            })
    })
    @SecurityRequirement(name = "jwt_auth")
    public List <JobEntity> findJobByFilter (@RequestParam String description) {
        return this.listAllJobsByFilterUseCase.execute(description);
    }


    @PostMapping("/job/apply")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation (summary = "Apply Job for Candidates", description = "This endpoint allows candidates to apply for jobs based on a description filter.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = ApplyJobEntity.class)))
            })
    })
    public ResponseEntity<Object> applyJob(HttpServletRequest request, @RequestBody UUID idJob) {
        var idCandidate = request.getAttribute("candidate_id");

        try {
            var result = this.applyJobCandidateUseCase.execute(UUID.fromString(idCandidate.toString()),idJob);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

    }

    }
}
