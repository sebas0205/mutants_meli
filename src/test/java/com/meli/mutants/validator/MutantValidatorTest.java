package com.meli.mutants.validator;


import com.meli.mutants.MutantsApplication;
import com.meli.mutants.config.MutantProperties;
import com.meli.mutants.controller.Validator.DnaValidator;
import com.meli.mutants.controller.dto.request.MutantRequest;
import com.meli.mutants.exceptions.MutantValidatorException;
import org.hibernate.service.spi.InjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MutantValidatorTest {



    @Autowired
    DnaValidator validator;

    @Test
    public void isDnaSquareWrong () throws MutantValidatorException {

        MutantRequest request = new MutantRequest();
        request.setDna(Arrays.asList(
                (       "GGGGGT," +
                        "CAGCCC").split(",") ));

        assertFalse(validator.isSquareMatrix(request.getDna()));

    }

    @Test
    public void isDnaSquareRight () throws MutantValidatorException {

        MutantRequest request = new MutantRequest();
        request.setDna(Arrays.asList(
                (       "GGGG," +
                        "CAGC,"+
                        "CAGC,"+
                        "CAGC,").split(",") ));

        assertTrue(validator.isSquareMatrix(request.getDna()));

    }


    @Test
    public void isinvalidLenght () throws MutantValidatorException {

        String chain = "ACT";
        assertFalse(validator.isLenghtOK(chain));

    }

    @Test
    public void NotHasValidCharactestesWrong () throws MutantValidatorException {

        String chain = "PPXACT";
        assertFalse(validator.hasValidCharacters(chain));

    }
}
