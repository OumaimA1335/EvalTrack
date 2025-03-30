package com.EvalTrack.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EvalTrack.Entities.Etudiant;
import com.EvalTrack.Entities.Examen;
import com.EvalTrack.Entities.Matiére;
import com.EvalTrack.Repositories.EtudiantRepository;
import com.EvalTrack.Repositories.ExamenRepository;

@Service
public class ExamenService {

	private final ExamenRepository examenRepo;
	private final EtudiantRepository etudiantRepository ;
    
	@Autowired
	public ExamenService(ExamenRepository examenRepo,EtudiantRepository etudiantRepository ) {
		super();
		this.examenRepo = examenRepo;
		this.etudiantRepository=etudiantRepository;
	}
	public List<Examen> getAllExams() {
        return examenRepo.findAll();
    }

    public Examen getExamById(int id) {
        return examenRepo.findById(id).orElse(null);
    }

    public Examen saveExam(Examen newExam) {
    	  Examen exam = new Examen();
          exam.setTypeExam(newExam.getTypeExam());
          exam.setNotes(newExam.getNotes());
          exam.setLienCopie(newExam.getLienCopie());

          // Récupérer la matière si l'ID est fourni
          if (newExam.getMatiere() != null) {
              //Matiére matiere = matiereRepository.findById(newExam.getMatiere()).orElse(null);
             // exam.setMatiere(matiere);
          } else {
              exam.setMatiere(null);
          }

          // Récupérer l'étudiant si l'ID est fourni
          if (newExam.getEtudiant() != null) {
              Etudiant etudiant = etudiantRepository.findById(newExam.getEtudiant().getId()).orElse(null);
              exam.setEtudiant(etudiant);
          } else {
              exam.setEtudiant(null);
          }

          return examenRepo.save(exam);
      }
    
    
    public void deleteExam(int id) {
        examenRepo.deleteById(id);
    }
    
    public List<Examen> findByStudent(int id) {
        return examenRepo.findByEtudiantId(id);
    }
    public List<Examen> findBySubject(int id) {
        return examenRepo.findByMatiereId(id);
    }
    public List<Examen> findByType(String type) {
        return examenRepo.findByTypeExam(type);
    }
    public Examen updateExam(int id, Examen updatedExam) {
       
        if (this.getExamById(id) != null) {
            Examen exam = this.getExamById(id);
            exam.setTypeExam(updatedExam.getTypeExam());
            exam.setNotes(updatedExam.getNotes());
            exam.setLienCopie(updatedExam.getLienCopie());
            if (updatedExam.getMatiere() != null) {
                //Matiére matiere = matiereRepository.findById(newExam.getMatiere()).orElse(null);
               // exam.setMatiere(matiere);
            } else {
                exam.setMatiere(null);
            }
            if (updatedExam.getEtudiant() != null) {
                Etudiant etudiant = etudiantRepository.findById(updatedExam.getEtudiant().getId()).orElse(null);
                exam.setEtudiant(etudiant);
            } else {
                exam.setEtudiant(null);
            }
            return examenRepo.save(exam);
        } else {
            return null; 
        }
    }
	
}
