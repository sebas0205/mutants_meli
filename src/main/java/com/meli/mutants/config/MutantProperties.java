package com.meli.mutants.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("mutant")
public class MutantProperties {

    private  int requiredLenght ;
    private  String charactersAcepted ;
    private  int minSequence ;

    public String getPatternAcepted(){
        return "["+charactersAcepted+"]+";
    }

    public char[] getCharactersAcepted(){
        return charactersAcepted.toCharArray();
    }

}
