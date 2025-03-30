package com.EvalTrack.Entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;

@Entity
public class Section {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer SectionId;

	public Integer getSectionId() {
		return SectionId;
	}

	public void setSectionId(Integer sectionId) {
		SectionId = sectionId;
	}
	
}
