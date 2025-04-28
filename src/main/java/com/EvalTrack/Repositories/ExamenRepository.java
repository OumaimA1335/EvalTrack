package com.EvalTrack.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.EvalTrack.Entities.Examen;

public interface ExamenRepository extends JpaRepository<Examen, Integer> {

    @Query("SELECT e FROM Examen e WHERE e.etudiant.id = :etudiantId")
    List<Examen> findByEtudiantId(@Param("etudiantId") int etudiantId);

    @Query("SELECT e FROM Examen e WHERE e.matiere.id = :matiereId")
    List<Examen> findByMatiereId(@Param("matiereId") int matiereId);

    List<Examen> findByTypeExam(String typeExam);

    @Query("SELECT e FROM Examen e WHERE e.section.idSection = :sectionId")
    List<Examen> findBySectionId(@Param("sectionId") int sectionId);

    @Query("SELECT e FROM Examen e WHERE e.module.idModule = :moduleId")
    List<Examen> findByModuleId(@Param("moduleId") int moduleId);
}
