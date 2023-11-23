package com.iwa.candidats.controller;

import com.iwa.candidats.model.Candidat;
import com.iwa.candidats.service.CandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/candidats")
public class CandidatController {

    @Autowired
    private CandidatService candidatService;

    // Get all candidats
    @GetMapping
    public List<Candidat> getAllCandidats() {
        return candidatService.getAllCandidats();
    }

    // Get a single candidat by email
    @GetMapping("/{email}")
    public ResponseEntity<?> getCandidatByEmail(@PathVariable String email) {
        Candidat candidat = candidatService.getCandidatByEmail(email);
        if (candidat != null) {
            return ResponseEntity.ok(candidat);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Create a new candidat
    @PostMapping
    public ResponseEntity<Candidat> createCandidat(@RequestBody Candidat candidat) {
        Candidat createdCandidat = candidatService.createOrUpdateCandidat(candidat);
        // La réponse URI devrait idéalement indiquer l'emplacement du nouveau candidat créé
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{email}")
                .buildAndExpand(createdCandidat.getEmail())
                .toUri();
        return ResponseEntity.created(location).body(createdCandidat);
    }


    // Update a candidat
    @PutMapping("/{email}")
    public ResponseEntity<Candidat> updateCandidat(@PathVariable String email, @RequestBody Candidat candidatDetails) {
        candidatDetails.setEmail(email); // Ensure the email is set correctly
        Candidat updatedCandidat = candidatService.createOrUpdateCandidat(candidatDetails);
        return ResponseEntity.ok(updatedCandidat);
    }


    @PutMapping("/update-state/{email}")
    public ResponseEntity<?> updateCandidatState(@PathVariable String email, @RequestParam String etat) {
        try {
            Candidat candidat = candidatService.updateCandidatState(email, etat);
            return ResponseEntity.ok(candidat);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    // Delete a candidat
    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteCandidat(@PathVariable String email) {
        candidatService.deleteCandidatByEmail(email);
        return ResponseEntity.ok().build();
    }

}
