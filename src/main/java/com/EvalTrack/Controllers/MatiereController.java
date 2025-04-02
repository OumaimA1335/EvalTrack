package com.EvalTrack.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.EvalTrack.Entities.Matiére;
import com.EvalTrack.Repositories.MatiereRepository;

@RestController
@RequestMapping("/api/matieres")
public class MatiereController {

    @Autowired
    private MatiereRepository matiereRepository;

    // Récupérer toutes les matières
    @GetMapping
    public List<Matiére> getAllMatieres() {
        return matiereRepository.findAll();
    }

    // Récupérer une matière par ID
    @GetMapping("/{id}")
    public ResponseEntity<Matiére> getMatiereById(@PathVariable Integer id) {
        Optional<Matiére> matiere = matiereRepository.findById(id);
        return matiere.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Ajouter une nouvelle matière
    @PostMapping
    public Matiére createMatiere(@RequestBody Matiére matiere) {
        return matiereRepository.save(matiere);
    }

    // Modifier une matière existante
    @PutMapping("/{id}")
    public ResponseEntity<Matiére> updateMatiere(@PathVariable Integer id, @RequestBody Matiére matiereDetails) {
        Optional<Matiére> optionalMatiere = matiereRepository.findById(id);
        if (optionalMatiere.isPresent()) {
            Matiére matiere = optionalMatiere.get();
            matiere.setNom(matiereDetails.getNom());
            matiere.setDescription(matiereDetails.getDescription());
            matiere.setEnseignant(matiereDetails.getEnseignant());
            matiere.setSection(matiereDetails.getSection());
            return ResponseEntity.ok(matiereRepository.save(matiere));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer une matière
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatiere(@PathVariable Integer id) {
        if (matiereRepository.existsById(id)) {
            matiereRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    

    // 🔹 Récupérer les matières d'un enseignant spécifique
    @GetMapping("/enseignant/{enseignantId}")
    public ResponseEntity<List<Matiére>> getMatieresByEnseignant(@PathVariable Integer enseignantId) {
        List<Matiére> matieres = matiereRepository.findByEnseignantEnseignantId(enseignantId);
        if (matieres.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(matieres);
    }
}
