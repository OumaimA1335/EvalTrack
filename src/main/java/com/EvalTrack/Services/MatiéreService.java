package com.EvalTrack.Services;

import com.EvalTrack.Entities.Matiére;
import com.EvalTrack.Repositories.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatiéreService {

    @Autowired
    private MatiereRepository matiereRepository;

    // Ajouter une matière
    public Matiére ajouterMatiere(Matiére matiere) {
        return matiereRepository.save(matiere);
    }

    // Récupérer toutes les matières
    public List<Matiére> getAllMatieres() {
        return matiereRepository.findAll();
    }

    // Récupérer une matière par ID
    public Optional<Matiére> getMatiereById(Integer id) {
        return matiereRepository.findById(id);
    }

    // Mettre à jour une matière
    public Matiére updateMatiere(Integer id, Matiére matiereDetails) {
        Optional<Matiére> optionalMatiere = matiereRepository.findById(id);
        if (optionalMatiere.isPresent()) {
            Matiére matiere = optionalMatiere.get();
            matiere.setNom(matiereDetails.getNom());
            matiere.setDescription(matiereDetails.getDescription());
            matiere.setCoefficient(matiereDetails.getCoefficient());
            matiere.setMoyenne(matiereDetails.getMoyenne());
            matiere.setPonderation(matiereDetails.getPonderation());
            matiere.setEnseignant(matiereDetails.getEnseignant());
            matiere.setSection(matiereDetails.getSection());
            return matiereRepository.save(matiere);
        } else {
            throw new RuntimeException("Matiére non trouvée avec l'ID : " + id);
        }
    }

    // Supprimer une matière
    public void deleteMatiere(Integer id) {
        matiereRepository.deleteById(id);
    }
}
