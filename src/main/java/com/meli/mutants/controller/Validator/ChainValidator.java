package com.meli.mutants.controller.Validator;

import com.meli.mutants.config.MutantProperties;
import com.meli.mutants.exceptions.MutantValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ChainValidator {

     @Autowired
     MutantProperties properties;


     public boolean validate(String dna) throws MutantValidatorException {
          return  isLenghtOK(dna) && hasValidCharacters(dna);
     }

     private boolean isLenghtOK(String chain ) throws MutantValidatorException {
          if (chain.length() < properties.getRequiredLenght()){
              throw new MutantValidatorException("La longitud de la cadena es invalida");
          }
          return true;
     }

     private boolean hasValidCharacters(String dna) throws MutantValidatorException {
          Pattern pattern = Pattern.compile(properties.getPatternAcepted(), Pattern.CASE_INSENSITIVE);

          if (!pattern.matcher(dna).matches()){
               throw new MutantValidatorException("La longitud de la cadena es invalida");
          }
          return true;
     }



}
