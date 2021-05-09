package com.meli.mutants.controller.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class MutantRequest
{
    @NotNull
    private List<String> dna ;

}
