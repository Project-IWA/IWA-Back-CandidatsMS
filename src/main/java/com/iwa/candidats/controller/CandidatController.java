package com.iwa.candidats.controller;

import com.iwa.candidats.model.Candidat;
import com.iwa.candidats.service.CandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Candidat createCandidat(@RequestBody Candidat candidat) {
        return candidatService.createOrUpdateCandidat(candidat);
    }

    // Update a candidat
    @PutMapping("/{email}")
    public ResponseEntity<?> updateCandidat(@PathVariable String email, @RequestBody Candidat candidatDetails) {
        Candidat existingCandidat = candidatService.getCandidatByEmail(email);
        if (existingCandidat != null) {
            // Update and save candidat details
            candidatDetails.setEmail(email); // Ensure the email is set correctly
            Candidat updatedCandidat = candidatService.createOrUpdateCandidat(candidatDetails);
            return ResponseEntity.ok(updatedCandidat);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a candidat
    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteCandidat(@PathVariable String email) {
        if (candidatService.deleteCandidatByEmail(email)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
