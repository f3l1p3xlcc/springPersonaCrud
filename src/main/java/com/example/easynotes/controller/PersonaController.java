package com.example.easynotes.controller;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Persona;
import com.example.easynotes.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * Created by felipegatica on 29/05/18.
 */
@RestController
@RequestMapping("/api")
public class PersonaController {

    @Autowired
    PersonaRepository personaRepository;

    /*
     * Lista todas las personas
     */
    @GetMapping("/personas")
    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }

    /*
     * Crea una nueva persona
     */
    @PostMapping(path = "/personas", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Persona createPersona(@Valid @RequestBody Persona persona) {

        return personaRepository.save(persona);
    }

    /*
     * Lista una persona por id
     */
    @GetMapping("/personas/{idpersona}")
    public Persona getPersonaById(@PathVariable(value = "idpersona") int personaId) {
        return personaRepository.findById(personaId)
                .orElseThrow(() -> new ResourceNotFoundException("Persona", "idPersona", personaId));
    }


    /*
     * Edita la información de una persona por id
     */
    @PutMapping("/personas/{id}")
    public Persona updatePersona(@PathVariable(value = "id") Integer personaId,
                           @Valid @RequestBody Persona personaDetails) {

        Persona persona = personaRepository.findById(personaId)
                .orElseThrow(() -> new ResourceNotFoundException("Persona", "id", personaId));

        persona.setNombre(personaDetails.getNombre());
        persona.setApellido(personaDetails.getApellido());

        Persona updatedPersona = personaRepository.save(persona);
        return updatedPersona;
    }

    /*
     * Borra una persona por id
     */
    @DeleteMapping(value = "/persona/{id}")
    public ResponseEntity<?> deletePersona(@PathVariable(value = "id") Integer personaId) {
        Persona persona = personaRepository.findById(personaId)
                .orElseThrow(() -> new ResourceNotFoundException("Persona", "id", personaId));

        personaRepository.delete(persona);
        return ResponseEntity.ok().build();
    }
}
