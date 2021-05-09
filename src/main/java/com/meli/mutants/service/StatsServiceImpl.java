package com.meli.mutants.service;

import com.meli.mutants.controller.dto.response.StatsResponse;
import com.meli.mutants.model.Human;
import com.meli.mutants.repository.MutantRepository;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


@Service
public class StatsServiceImpl implements StatsService{


    @Autowired
    MutantRepository repo ;

    @Override
    public StatsResponse getStats() {

        List<Human> humans = repo.findAll();
        StatsResponse response = new StatsResponse();

        response.setMutantCount(humans.stream().filter(human -> human.isMutant()).count() );
        response.setHumanCount (humans.stream().filter(human -> !human.isMutant()).count() );
        response.setRatio( calculateRatio(response));
        return  response;

    }


    private BigDecimal calculateRatio(StatsResponse status){

        BigDecimal ratio;
        if(status.getHumanCount()==0){
            ratio = BigDecimal.ONE;
        }else{
            BigDecimal mutant = BigDecimal.valueOf(status.getMutantCount());
            BigDecimal human = BigDecimal.valueOf(status.getHumanCount());
            ratio = mutant.divide(human, 2, RoundingMode.HALF_UP);
        }

        return ratio;
    }
}
