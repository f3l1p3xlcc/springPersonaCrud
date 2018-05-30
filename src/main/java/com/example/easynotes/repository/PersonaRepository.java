package com.example.easynotes.repository;

import com.example.easynotes.model.Note;
import com.example.easynotes.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by felipegatica on 29/05/18.
 */

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {

}
