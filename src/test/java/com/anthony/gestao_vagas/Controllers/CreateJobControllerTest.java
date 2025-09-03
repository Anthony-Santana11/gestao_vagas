package com.anthony.gestao_vagas.Controllers;

import com.anthony.gestao_vagas.Dto.CreateJobDTO;
import com.anthony.gestao_vagas.Entity.CompanyEntity;
import com.anthony.gestao_vagas.Exceptions.CompanyNotFoundException;
import com.anthony.gestao_vagas.Repository.CompanyRepository;
import com.anthony.gestao_vagas.utils.TestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.MediaType;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static com.anthony.gestao_vagas.utils.TestUtils.objectToJson;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateJobControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private CompanyRepository companyRepository;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void testCreateJob() throws Exception {

       var company = CompanyEntity.builder()
                .description("DESCRIPTION_TEST_COMPANY_TEST_TEST_TEST")
                    .email("email@company.com")
                        .password("12345678")
                            .username("USERNAME_TEST")
                                .name("NAME_TEST")
                                    .build();

       company = companyRepository.saveAndFlush(company);

      var createJobDTO =   CreateJobDTO.builder()
                        .benefits("BENEFITS_TEST")
                                .description("DESCRIPTION_TEST")
                                        .level("LEVEL_TEST")
                                                .build();


      var result = mvc.perform(
                      MockMvcRequestBuilders.post("/company/job/")
                              .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                                      .content(objectToJson(createJobDTO))
                                              .header("Authorization", "Bearer " + TestUtils.generateToken(company.getId()))


      ) .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldNotCreateJobWithoutCompany() throws Exception {
        var createJobDTO =   CreateJobDTO.builder()
                .benefits("BENEFITS_TEST")
                    .description("DESCRIPTION_TEST")
                        .level("LEVEL_TEST")
                            .build();

           try { mvc.perform(
                    MockMvcRequestBuilders.post("/company/job/")
                            .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                                .content(objectToJson(createJobDTO))
                                    .header("Authorization", "Bearer " + TestUtils.generateToken(UUID.randomUUID()))


            ); } catch (Exception ex) {
               Assertions.assertTrue(ex.getMessage().contains("Company not found"));
            }
    }

}
