package com.EvalTrack.Entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Module {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idModule;

    private String nomModule;
    private float moyenne;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Matiére> listeMatieres;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Examen> listeExams;

    // Constructeurs
    public Module() {}

    public Module(String nomModule, float moyenne) {
        this.nomModule = nomModule;
        this.moyenne = moyenne;
    }

    // Getters et Setters
    public int getIdModule() {
        return idModule;
    }

    public void setIdModule(int idModule) {
        this.idModule = idModule;
    }

    public String getNomModule() {
        return nomModule;
    }

    public void setNomModule(String nomModule) {
        this.nomModule = nomModule;
    }

    public float getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(float moyenne) {
        this.moyenne = moyenne;
    }

    public List<Matiére> getListeMatieres() {
        return listeMatieres;
    }

    public void setListeMatieres(List<Matiére> listeMatieres) {
        this.listeMatieres = listeMatieres;
    }

    public List<Examen> getListeExams() {
        return listeExams;
    }

    public void setListeExams(List<Examen> listeExams) {
        this.listeExams = listeExams;
    }
}