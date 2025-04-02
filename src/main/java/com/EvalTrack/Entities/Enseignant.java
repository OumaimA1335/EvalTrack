/**
 * 
 */
package com.EvalTrack.Entities;

/**
 * @author eyazi
 *
 */
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Enseignant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer enseignantId;

	@Column(nullable = false)
	private String nom;

	@Column(nullable = false)
	private String prenom;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String specialite;

	@OneToMany(mappedBy = "enseignant", cascade = CascadeType.ALL)
	private List<Matiére> matieres;
	
	
	
	
	public Enseignant(Integer enseignantId, String nom, String prenom, String email, String specialite,
			List<Matiére> matieres) {
		super();
		this.enseignantId = enseignantId;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.specialite = specialite;
		this.matieres = matieres;
	}

	

	public Integer getEnseignantId() {
		return enseignantId;
	}

	public void setEnseignantId(Integer enseignantId) {
		this.enseignantId = enseignantId;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

	public List<Matiére> getMatieres() {
		return matieres;
	}

	public void setMatieres(List<Matiére> matieres) {
		this.matieres = matieres;
	}
	
	

}
