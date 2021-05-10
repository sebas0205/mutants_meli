package com.meli.mutants.controller.Validator;

import com.meli.mutants.config.MutantProperties;
import com.meli.mutants.controller.dto.request.MutantRequest;
import com.meli.mutants.exceptions.MutantValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component
public class DnaValidator {


     Logger logger = LoggerFactory.getLogger(DnaValidator.class);

     @Autowired
     private MutantProperties properties;

     public boolean isValidDna(MutantRequest dna)  {
          try {
               if (!isSquareMatrix(dna.getDna()))
                    return false;

               for (int  chain = 0 ; chain< dna.getDna().size() ;chain++){
                    if (!validate(dna.getDna().get(chain))){
                         return false;
                    }
               }}catch (MutantValidatorException e){
                    logger.error(e.getMessage());
                    return false;
          }
          return true ;
     }

     private boolean validate(String dna) throws MutantValidatorException {
          return  isLenghtOK(dna) && hasValidCharacters(dna);
     }

     public boolean isLenghtOK(String chain ) throws MutantValidatorException {
          try{
               if (chain.length() < properties.getRequiredLenght()){
                   throw new MutantValidatorException("La longitud de la cadena es invalida");
               }
          }catch (Exception e){
               return false ;
          }
          return true;
     }

     public boolean hasValidCharacters(String dna) throws MutantValidatorException {
          Pattern pattern = Pattern.compile(properties.getPatternAcepted(), Pattern.CASE_INSENSITIVE);
          try {
               if (!pattern.matcher(dna).matches()){
                    throw new MutantValidatorException("La cadena contiene caracteres invalidos");
               }
          }catch (Exception e){
               return false ;
          }
          return true;
     }

     public boolean isSquareMatrix(List<String> dna) throws MutantValidatorException {
          int size = dna.size();
          try{
               for ( String row : dna){
                    if (row.length()  != size ){
                         throw new MutantValidatorException("La matriz de dna no es cuadrada");
                    }
               }
          }catch (Exception e){
               return false ;
          }
          return true ;
     }



}
