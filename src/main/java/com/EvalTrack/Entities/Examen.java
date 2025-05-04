package com.EvalTrack.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
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
    private String session;

    // Relation avec Matiere (Un examen appartient à une seule matière)
    @ManyToOne
    @JoinColumn(name = "matiereId") //pour le moment nulle
    private Matiére matiere;

    // Relation avec Etudiant (Un étudiant peut passer plusieurs examens)
    
    @ManyToOne
    @JoinColumn(name = "idEtudinat")
    private Etudiant etudiant;

	public Examen(int idExam, String typeExam, Double notes, String lienCopie, Matiére matiere, Etudiant etudiant,String session) {
		super();
		this.idExam = idExam;
		this.typeExam = typeExam;
		this.notes = notes;
		this.lienCopie = lienCopie;
		this.matiere = matiere!=null? matiere :null;;
		this.session=session;
		this.setEtudiant(etudiant);
	}
	

	public Examen() {
		super();
		// TODO Auto-generated constructor stub
	}


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


	public String getSession() {
		return session;
	}


	public void setSession(String session) {
		this.session = session;
	}
	
    

}
