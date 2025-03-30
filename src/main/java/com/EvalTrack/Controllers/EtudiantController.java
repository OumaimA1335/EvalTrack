package com.EvalTrack.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.EvalTrack.Entities.Administrateur;
import com.EvalTrack.Entities.Etudiant;
import com.EvalTrack.Services.EtudiantService;

@RestController
@RequestMapping("/etudiant")
@CrossOrigin(origins = "http://localhost:8080")
public class EtudiantController {
	private final EtudiantService EtdService;
	@Autowired
	public EtudiantController(EtudiantService EtdService)
	{
		this.EtdService=EtdService;
	}
	
	@PostMapping("/CreateEtudiant")
	@ResponseBody
	public ResponseEntity<String> createEudiant(@RequestBody Etudiant ETD)
	{
		System.out.println(ETD.getCin());
		Etudiant createEtudiant = EtdService.createEtudiant(ETD);
		if(createEtudiant!=null)
		{
			return ResponseEntity.ok("Etudiant créé avec succés");
			
		}else
		{
			 return ResponseEntity.status(400).body("Erreur lors de la création de Etudiant");
		}
	}
	
	@PostMapping("/LoginEtudiant")
	@ResponseBody
	public ResponseEntity<String> loginEtudiant(
	        @RequestBody Etudiant etudiant) {

	    String isAuthenticated = EtdService.login(etudiant.getEmail(),etudiant.getMotDePasse());

	    if (isAuthenticated != null) {
	        return ResponseEntity.ok("Connexion réussie !");
	    } else {
	        return ResponseEntity.status(401).body("Email ou mot de passe incorrect.");
	    }
	}
	 @PutMapping("/{id}")
	 public ResponseEntity<Etudiant> updateEtudiant(@PathVariable Long id, @RequestBody Etudiant newData) {
		 
		 return ResponseEntity.ok(EtdService.modifierEtudiant(id, newData)) ;
	 }
	 @GetMapping
	    public List<Etudiant> getAllStudents() {
	        return EtdService.getAllStudents();
	    }
	 @GetMapping("/{id}")
	    public Optional<Etudiant> getStudent(@PathVariable Long id) {
	        return EtdService.getEtudiantById(id);
	    }
	
}
