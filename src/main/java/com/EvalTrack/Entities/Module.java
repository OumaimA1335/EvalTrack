package com.EvalTrack.Entities;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Module {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idModule;

    private String nomModule;
    private float moyenne;
    @JsonIgnore
    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Matiére> listeMatieres;

    @ManyToOne
    @JoinColumn(name = "SectionId")
    private Section section;

    // Constructeurs
    public Module() {}
    

    public Module(int idModule) {
		super();
		this.idModule = idModule;
	}


	public Module(String nomModule, float moyenne,Section section) {
        this.nomModule = nomModule;
        this.moyenne = moyenne;
        this.section=section;
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

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

   
}