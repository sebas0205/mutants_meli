package com.meli.mutants.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Human {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id ;

    @Column
    private String dna ;

    @Column( name = "mutant")
    private boolean isMutant;


}
