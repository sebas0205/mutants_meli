package com.meli.mutants.controller.dto.response;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class StatsResponse {


    @JsonProperty("count_mutant_dna")
    private Long mutantCount;

    @JsonProperty("count_human_dna")
    private Long humanCount;

    @JsonProperty("ratio")
    private BigDecimal ratio ;

    @JsonIgnore
    private Long total = 0L;


}
