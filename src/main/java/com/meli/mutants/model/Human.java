package com.meli.mutants.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
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

    @Override
    public boolean equals(Object o){
        if (o instanceof  Human){
            return this.dna.equals(((Human) o).getDna());
        }else{
            return false;
        }

    }

}
