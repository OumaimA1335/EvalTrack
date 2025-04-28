package com.EvalTrack.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EvalTrack.Entities.Etudiant;
import com.EvalTrack.Entities.Examen;
import com.EvalTrack.Entities.Module;
import com.EvalTrack.Entities.Matiére;
import com.EvalTrack.Entities.Section;
import com.EvalTrack.Repositories.EtudiantRepository;
import com.EvalTrack.Repositories.ExamenRepository;
import com.EvalTrack.Repositories.ModuleRepository;
import com.EvalTrack.Repositories.SectionRepository;

@Service
public class ExamenService {

    private final ExamenRepository examenRepo;
    private final EtudiantRepository etudiantRepository;
    private final SectionRepository sectionRepository;
    private final ModuleRepository moduleRepository;

    @Autowired
    public ExamenService(ExamenRepository examenRepo, EtudiantRepository etudiantRepository,
                         SectionRepository sectionRepository, ModuleRepository moduleRepository) {
        this.examenRepo = examenRepo;
        this.etudiantRepository = etudiantRepository;
        this.sectionRepository = sectionRepository;
        this.moduleRepository = moduleRepository;
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
        exam.setSemestre(newExam.getSemestre());
        exam.setSession(newExam.getSession());

        // Récupérer la matière
        exam.setMatiere(newExam.getMatiere());

        // Récupérer l'étudiant
        if (newExam.getEtudiant() != null) {
            Etudiant etudiant = etudiantRepository.findById(newExam.getEtudiant().getId()).orElse(null);
            exam.setEtudiant(etudiant);
        }

        // Récupérer la section
        if (newExam.getSection() != null) {
            Section section = sectionRepository.findById(newExam.getSection().getIdSection()).orElse(null);
            exam.setSection(section);
        }

        // Récupérer le module
        if (newExam.getModule() != null) {
            Module module = moduleRepository.findById(newExam.getModule().getIdModule()).orElse(null);
            exam.setModule(module);
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
        Examen exam = this.getExamById(id);
        if (exam != null) {
            exam.setTypeExam(updatedExam.getTypeExam());
            exam.setNotes(updatedExam.getNotes());
            exam.setLienCopie(updatedExam.getLienCopie());
            exam.setSemestre(updatedExam.getSemestre());
            exam.setSession(updatedExam.getSession());

            // Mise à jour de la matière
            exam.setMatiere(updatedExam.getMatiere());

            // Mise à jour de l'étudiant
            if (updatedExam.getEtudiant() != null) {
                Etudiant etudiant = etudiantRepository.findById(updatedExam.getEtudiant().getId()).orElse(null);
                exam.setEtudiant(etudiant);
            } else {
                exam.setEtudiant(null);
            }

            // Mise à jour de la section
            if (updatedExam.getSection() != null) {
                Section section = sectionRepository.findById(updatedExam.getSection().getIdSection()).orElse(null);
                exam.setSection(section);
            } else {
                exam.setSection(null);
            }

            // Mise à jour du module
            if (updatedExam.getModule() != null) {
                Module module = moduleRepository.findById(updatedExam.getModule().getIdModule()).orElse(null);
                exam.setModule(module);
            } else {
                exam.setModule(null);
            }

            return examenRepo.save(exam);
        }
        return null;
    }
}
