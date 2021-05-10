package com.meli.mutants.controller.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
public class MutantRequest
{
    @NotNull
    private List<String> dna ;

}
