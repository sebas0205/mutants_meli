package com.meli.mutants.controller;

import com.meli.mutants.controller.dto.request.MutantRequest;
import com.meli.mutants.service.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MutantController {

    @Autowired
    private MutantService mutantService;

    @RequestMapping( value  ="/mutant" , method = RequestMethod.POST)
    public ResponseEntity isMutant(@RequestBody MutantRequest dna){
        boolean isMutant = mutantService.isMutant(dna);
        return isMutant? ok() :forbidden();
    }

    private ResponseEntity ok(){
        return new ResponseEntity(true,HttpStatus.OK);
    }

    private ResponseEntity forbidden(){
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }



}
