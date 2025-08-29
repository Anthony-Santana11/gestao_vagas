package com.anthony.gestao_vagas.Controllers;

import com.anthony.gestao_vagas.Dto.CreateJobDTO;
import com.anthony.gestao_vagas.utils.TestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
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
public class CreateJobControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void testCreateJob() throws Exception {

      var createJobDTO =   CreateJobDTO.builder()
                        .benefits("BENEFITS_TEST")
                                .description("DESCRIPTION_TEST")
                                        .level("LEVEL_TEST")
                                                .build();

        var result = mvc.perform(
                        MockMvcRequestBuilders.post("/company/job/")
                                .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                                .content(objectToJson(createJobDTO))
                                .header("Authorization", "Bearer " + TestUtils.generateToken(UUID.randomUUID()))
                )
                .andExpect(status().isOk());
    }

}
