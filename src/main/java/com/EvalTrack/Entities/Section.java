package com.EvalTrack.Entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;

@Entity
public class Section {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer SectionId;

	  private String nomSection;
	  private int semestre;

	    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private List<Etudiant> listeEtudiants;

	    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private List<Module> listeModules;

	    public Section() {}

	    public Section(String nomSection, int semestre) {
	        this.nomSection = nomSection;
	        this.semestre = semestre;
	    }

	    public int getIdSection() {
	        return SectionId;
	    }

	    public void setIdSection(int idSection) {
	        this.SectionId = idSection;
	    }

	    public Section(Integer sectionId, String nomSection, int semestre, List<Etudiant> listeEtudiants,
				List<Module> listeModules) {
			super();
			SectionId = sectionId;
			this.nomSection = nomSection;
			this.semestre = semestre;
			this.listeEtudiants = listeEtudiants;
			this.listeModules = listeModules;
		}

		public String getNomSection() {
	        return nomSection;
	    }

	    public void setNomSection(String nomSection) {
	        this.nomSection = nomSection;
	    }

	    public int getSemestre() {
	        return semestre;
	    }

	    public void setSemestre(int semestre) {
	        this.semestre = semestre;
	    }

	    public List<Etudiant> getListeEtudiants() {
	        return listeEtudiants;
	    }

	    public void setListeEtudiants(List<Etudiant> listeEtudiants) {
	        this.listeEtudiants = listeEtudiants;
	    }

	    public List<Module> getListeModules() {
	        return listeModules;
	    }

	    public void setListeModules(List<Module> listeModules) {
	        this.listeModules = listeModules;
	    }
	}

