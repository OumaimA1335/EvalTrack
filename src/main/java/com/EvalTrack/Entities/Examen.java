package com.EvalTrack.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Examen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idExam;
    
    private String typeExam;

    private Double notes;

    private String lienCopie;

    // Relation avec Matiere (Un examen appartient à une seule matière)
    @ManyToOne
    @JoinColumn(name = "matiereId", nullable = true)
    private Matiére matiere;

    // Relation avec Etudiant
    @ManyToOne
    @JoinColumn(name = "idEtudiant")
    private Etudiant etudiant;

    // ************ Ajouts demandés ************

    // Relation avec Section
    @ManyToOne
    @JoinColumn(name = "sectionId")
    private Section section;

    // Relation avec Module
    @ManyToOne
    @JoinColumn(name = "idModule")
    private Module module;

    // Semestre (Enum: SEMESTRE1 ou SEMESTRE2)
    @Enumerated(EnumType.STRING)
    private Semestre semestre;

    // Session (Enum: PRINCIPAL ou RATTRAPAGE)
    @Enumerated(EnumType.STRING)
    private Session session;

    // *******************************************

    // Constructeurs
    public Examen() {
        super();
    }

    public Examen(int idExam, String typeExam, Double notes, String lienCopie, Matiére matiere,
                  Etudiant etudiant, Section section, Module module, Semestre semestre, Session session) {
        this.idExam = idExam;
        this.typeExam = typeExam;
        this.notes = notes;
        this.lienCopie = lienCopie;
        this.matiere = matiere;
        this.etudiant = etudiant;
        this.section = section;
        this.module = module;
        this.semestre = semestre;
        this.session = session;
    }

    // Getters et Setters
    public int getIdExam() {
        return idExam;
    }

    public void setIdExam(int idExam) {
        this.idExam = idExam;
    }

    public String getTypeExam() {
        return typeExam;
    }

    public void setTypeExam(String typeExam) {
        this.typeExam = typeExam;
    }

    public Double getNotes() {
        return notes;
    }

    public void setNotes(Double notes) {
        this.notes = notes;
    }

    public String getLienCopie() {
        return lienCopie;
    }

    public void setLienCopie(String lienCopie) {
        this.lienCopie = lienCopie;
    }

    public Matiére getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiére matiere) {
        this.matiere = matiere;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
