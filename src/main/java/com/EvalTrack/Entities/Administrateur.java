package com.EvalTrack.Entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class Administrateur extends Utilisateur {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer id;
	public Administrateur(Integer id, String nom, String email, String motDePasse) {
		super(nom != null ? nom : "", email != null ? email : "", motDePasse != null ? motDePasse : "");
	    this.id = id != null ? id : 0; 
			
	}

	public Administrateur() {
		super();
		
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	

}
