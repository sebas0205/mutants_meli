package com.meli.mutants.service;

import com.meli.mutants.controller.Validator.ChainValidator;
import com.meli.mutants.controller.detector.Detector;
import com.meli.mutants.controller.dto.request.MutantRequest;

import com.meli.mutants.exceptions.MutantValidatorException;
import com.meli.mutants.model.Human;
import com.meli.mutants.repository.MutantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MutantServiceImpl implements  MutantService {

   @Autowired private ChainValidator validator ;

   @Autowired private Detector mutantDetector;

   @Autowired private MutantRepository repo ;


    Logger logger = LoggerFactory.getLogger(MutantServiceImpl.class);

    @Override
    public Boolean isMutant(MutantRequest dna)  {

            if(!isValidDNA(dna))
                return false ;

            String dnaHuman = String.join("-",dna.getDna());
            char[][]  dnaMatrix=dnaToMatrix(dna);

            Optional<Human> human =  repo.findByDna(dnaHuman) ;

            if (human.isPresent()){
                return human.get().isMutant();
            }else{
                Human newHuman = Human.builder().dna(dnaHuman).isMutant(mutantDetector.isMutant(dnaMatrix)).build();
                repo.save(newHuman);
                return newHuman.isMutant();
            }
    }

    private boolean isValidDNA(MutantRequest dna)  {
        try {
            for (int  j = 0 ; j< dna.getDna().size() ;j++){
                if (!validator.validate(dna.getDna().get(j))){
                    return false;
                }
            }}catch (MutantValidatorException e){
            logger.error(e.getMessage());
            return false;
        }
        return  true;
    }

    private char[][] dnaToMatrix(MutantRequest dna){

        int dnaSize =  dna.getDna().size();
        char[][] dnaMatrix = new char[dnaSize][dnaSize];

        for (int  j = 0 ; j< dna.getDna().size() ;j++){
            dnaMatrix[j]=dna.getDna().get(j).toCharArray();
        }

        return dnaMatrix;
    }



}
