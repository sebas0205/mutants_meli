package com.meli.mutants.controller;


import com.meli.mutants.controller.dto.request.MutantRequest;
import com.meli.mutants.controller.dto.response.StatsResponse;
import com.meli.mutants.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatsController {

    @Autowired
    StatsService statsService ;

    @RequestMapping( value  ="/stats" , method = RequestMethod.GET)
    public ResponseEntity isMutant(){
        return ResponseEntity.ok( statsService.getStats());
    }


}
