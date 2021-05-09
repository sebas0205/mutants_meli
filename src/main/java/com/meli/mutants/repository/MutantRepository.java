package com.meli.mutants.repository;


import com.meli.mutants.model.Human;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MutantRepository extends JpaRepository<Human,Integer> {

    Optional<Human> findByDna(String dna);

}
