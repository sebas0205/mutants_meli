package com.meli.mutants.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.mutants.MutantsApplication;
import com.meli.mutants.controller.dto.request.MutantRequest;
import com.meli.mutants.controller.dto.response.StatsResponse;
import com.meli.mutants.repository.MutantRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MutantsApplication.class)
@AutoConfigureMockMvc
public class StatsControllerTest {


    @Autowired
    private MockMvc mock;

    @Autowired
    ObjectMapper objectmapper;

    @Autowired
    private MutantRepository repository;

    @Before
    public void cleanData(){
        repository.deleteAll();
    }



    @Test
    public void testGetStats() throws Exception {

        mock.perform(MockMvcRequestBuilders.get("/stats")).
                andExpect(status().isOk()).andReturn();
    }

    @Test
    public void testIsStatsRight () throws Exception {
        // is HUMAN
        MutantRequest request = new MutantRequest();
        request.setDna(Arrays.asList(
                       ("AGGGCT,"+
                        "CAGTGC,"+
                        "ATATGT,"+
                        "AGACGG,"+
                        "CCACTA,"+
                        "TCACTG").split(",") ));

        mock.perform(MockMvcRequestBuilders.post("/mutant").
                contentType(MediaType.APPLICATION_JSON).
                content(objectmapper.writeValueAsString(request))).
                andExpect(status().isForbidden());


        // is HUMAN
        request.setDna(Arrays.asList(
                (       "GGGTC,"+
                        "CAGTG,"+
                        "TTATG,"+
                        "AGAAG,"+
                        "CCACT").split(",") ));

        mock.perform(MockMvcRequestBuilders.post("/mutant").
                contentType(MediaType.APPLICATION_JSON).
                content(objectmapper.writeValueAsString(request))).
                andExpect(status().isForbidden());

        // IS Mutant
        request.setDna(Arrays.asList(
                       ("GGGGGT,"+
                        "CAGTGC,"+
                        "TTATGT,"+
                        "AGAAGG,"+
                        "CCACTA,"+
                        "TCACTG").split(",") ));

        mock.perform(MockMvcRequestBuilders.post("/mutant").
                contentType(MediaType.APPLICATION_JSON).
               content(objectmapper.writeValueAsString(request))).
                andExpect(status().isOk());

        String response = mock.perform(MockMvcRequestBuilders.get("/stats")).
                andExpect(status().isOk()).andReturn().getResponse().getContentAsString();


        StatsResponse stats = objectmapper.readValue(response, StatsResponse.class);
        System.out.println(stats.getHumanCount());
        assertEquals(stats.getHumanCount(),Long.valueOf(2));
        assertEquals(stats.getMutantCount(), Long.valueOf(1));
        assertEquals(stats.getRatio(), BigDecimal.valueOf(0.5).setScale(2));


    }


}
