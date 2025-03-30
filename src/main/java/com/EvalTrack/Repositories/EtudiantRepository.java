package com.EvalTrack.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EvalTrack.Entities.Etudiant;




public interface EtudiantRepository extends JpaRepository <Etudiant,Long> {
	 Optional<Etudiant> findByEmail(String email);
	              
	 List<Etudiant> findAll();
}

