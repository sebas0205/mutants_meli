package com.meli.mutants.service;


import com.meli.mutants.MutantsApplication;
import com.meli.mutants.controller.dto.request.MutantRequest;
import com.meli.mutants.model.Human;
import com.meli.mutants.repository.MutantRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MutantsApplication.class)
public class MutantServiceTest {

    @Autowired
    MutantService service;

    @Autowired
    private MutantRepository repository;

    @Before
    public void cleanData(){
        repository.deleteAll();
    }

    @Test
    public void testDNAIsMutantMixed() throws Exception {
        MutantRequest request = new MutantRequest();
        request.setDna(Arrays.asList((
                        "AAATGT,"+
                        "CAGTGC,"+
                        "TTATGT,"+
                        "AGAAGG,"+
                        "CCCCTA,"+
                        "TCACTG").split(",") ));


        assertTrue(service.isMutant(request) );
    }

    @Test
    public void testDNAIsMutantHorizontal() throws Exception {
        MutantRequest request = new MutantRequest();
        request.setDna(Arrays.asList((
                        "AAATGT,"+
                        "CAGCCC,"+
                        "TTTTGT,"+
                        "AGAAGG,"+
                        "CCCCTA,"+
                        "TCACTG").split(",") ));


        assertTrue(service.isMutant(request) );
    }

    @Test
    public void testDNAIsMutantVertical() throws Exception {
        MutantRequest request = new MutantRequest();
        request.setDna(Arrays.asList((
                "AAATGT,"+
                        "AAGTGC,"+
                        "ATATGT,"+
                        "AGATGG,"+
                        "CACCTA,"+
                        "TCACTG").split(",") ));


        assertTrue(service.isMutant(request) );
    }

    @Test
    public void testDNAIsMutantDiagonal() throws Exception {
        MutantRequest request = new MutantRequest();
        request.setDna(Arrays.asList((
                        "ACCTGT,"+
                        "TAGTGG,"+
                        "ATATGT,"+
                        "AGTGGG,"+
                        "CAGTTA,"+
                        "TCACTG").split(",") ));


        assertTrue(service.isMutant(request) );
    }


    @Test
    public void testDNAIsMutantDiagonalUp() throws Exception {
        MutantRequest request = new MutantRequest();
        request.setDna(Arrays.asList((
                        "CCCTGT,"+
                        "CAGTTG,"+
                        "ATATGT,"+
                        "AGTGGG,"+
                        "CAGTTA,"+
                        "TCACAG").split(",") ));


        assertTrue(service.isMutant(request) );
    }


    @Test
    public void testDNAExistinDataBase() throws Exception {

        List<String> dna =  Arrays.asList((
                      "AAAAGT,"+
                      "CAGTGC,"+
                      "TTATGT,"+
                      "AGAAGG,"+
                      "CCACTA,"+
                      "TCACTG").split(",") );

        MutantRequest request = new MutantRequest();
        request.setDna(dna);

        //Create record
        boolean isMutant = service.isMutant(request);
        Optional<Human> created = repository.findByDna(String.join("-",dna));

        assertEquals(isMutant,created.get().isMutant() );
    }


    @Test
    public void testDNAReturnFromDataBase() throws Exception {

        List<String> dna =  Arrays.asList((
                "AAAAGT,"+
                        "CAGTGC,"+
                        "TTATGT,"+
                        "AGAAGG,"+
                        "CCACTA,"+
                        "TCACTG").split(",") );

        MutantRequest request = new MutantRequest();
        request.setDna(dna);

        //Create record
        boolean isMutant = service.isMutant(request);
        boolean isMutantRepeted = service.isMutant(request);

        assertEquals(isMutant,isMutantRepeted );
    }

}
