package com.EvalTrack.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
@Entity
public class Matiére {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer matiereId;
	@JsonIgnore
	@OneToMany(mappedBy = "matiere", cascade = CascadeType.ALL)
	private List<Examen> exams;

	

	public Matiére(Integer matiereId, List<Examen> exams) {
		super();
		this.matiereId = matiereId;
		this.exams = exams;
	}

	public List<Examen> getExams() {
		return exams;
	}

	public void setExams(List<Examen> exams) {
		this.exams = exams;
	}

	public Integer getMatiereId() {
		return matiereId;
	}

	public void setMatiereId(Integer matiereId) {
		this.matiereId = matiereId;
	}
	
}
