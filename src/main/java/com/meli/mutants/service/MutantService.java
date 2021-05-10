package com.meli.mutants.service;

import com.meli.mutants.controller.dto.request.MutantRequest;
import com.meli.mutants.exceptions.MutantValidatorException;
import org.springframework.stereotype.Service;

@Service
public interface MutantService {

    Boolean isMutant(MutantRequest dna);

}
