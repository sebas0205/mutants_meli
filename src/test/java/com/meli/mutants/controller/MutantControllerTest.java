package com.meli.mutants.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.mutants.MutantsApplication;
import com.meli.mutants.controller.dto.request.MutantRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = MutantsApplication.class)
@AutoConfigureMockMvc
public class MutantControllerTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    ObjectMapper objectmapper;






    @Test
    public void testDNAIsHuman() throws Exception {
        MutantRequest request = new MutantRequest();
        request.setDna(Arrays.asList(
                (       "GGGGAT,"+
                        "CAGTGC,"+
                        "TTATGT,"+
                        "AGAAGG,"+
                        "CCACTA,"+
                        "TCCCTG").split(",") ));

        ResultActions result = mock.perform(MockMvcRequestBuilders.post("/mutant").
                contentType(MediaType.APPLICATION_JSON).
                content(objectmapper.writeValueAsString(request))).
                andExpect(status().isForbidden());

        System.out.println(result); ;
    }


    @Test
    public void testDNAIsMutant() throws Exception {
        MutantRequest request = new MutantRequest();
        request.setDna(Arrays.asList((
                "AAAAGT,"+
                        "CAGTGC,"+
                        "TTATGT,"+
                        "AGAAGG,"+
                        "CCACTA,"+
                        "TCACTG").split(",") ));

        String result = mock.perform(MockMvcRequestBuilders.post("/mutant").
                contentType(MediaType.APPLICATION_JSON).
                content(objectmapper.writeValueAsString(request))).
                andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        assertEquals(result,"true");
    }
}
