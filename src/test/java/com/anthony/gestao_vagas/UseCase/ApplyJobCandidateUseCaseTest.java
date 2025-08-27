package com.anthony.gestao_vagas.UseCase;

import com.anthony.gestao_vagas.Entity.CandidateEntity;
import com.anthony.gestao_vagas.Exceptions.JobNotFoundException;
import com.anthony.gestao_vagas.Exceptions.UserNotFoundException;
import com.anthony.gestao_vagas.Repository.CandidateRepository;
import com.anthony.gestao_vagas.Repository.JobRepository;
import com.anthony.gestao_vagas.modules.candidate.useCases.ApplyJobCandidateUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.assertj.core.api.Assertions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;



    @Test
    @DisplayName("Should not be able to apply job if candidate not exists")
    public void shouldNotBeToApplyJobIfCandidateNotExists() {
        try {
            applyJobCandidateUseCase.execute(null, null);
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(UserNotFoundException.class);
            System.out.println("Candidato nao encontrado");
        }

    }

    @Test
    @DisplayName("Should not be able to apply job if job not exists")
    public void ShouldNotBeToApplyJobIfJobNotExists() {
        var candidateId = java.util.UUID.randomUUID();

        var candidate = new CandidateEntity();
        candidate.setId(candidateId);

       when(candidateRepository.findById(candidateId)).thenReturn(java.util.Optional.of(candidate));

        try {
            applyJobCandidateUseCase.execute(candidateId, null);
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(JobNotFoundException.class);
            System.out.println("Vaga nao encontrada");
        }

    }

}
