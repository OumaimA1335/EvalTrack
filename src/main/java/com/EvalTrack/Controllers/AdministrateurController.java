package com.EvalTrack.Controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.EvalTrack.Entities.Administrateur;
import com.EvalTrack.Entities.Etudiant;
import com.EvalTrack.Services.AdministrateurService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:8080")
public class AdministrateurController {

	private final AdministrateurService adminService;
	@Autowired
	public AdministrateurController(AdministrateurService adminService)
	{
		this.adminService=adminService;
	}
	
	
	@PostMapping("/CreateAdmin")
	@ResponseBody
	public ResponseEntity<String> createAdministrateur(@RequestBody Administrateur admin)
	{
		Administrateur createdAdmin = adminService.createAdministrateur(admin);
		if(createdAdmin!=null)
		{
			return ResponseEntity.ok("Administrateur créé avec succés");
			
		}else
		{
			 return ResponseEntity.status(400).body("Erreur lors de la création de l'administrateur");
		}
	}
	
	@PostMapping("/LoginAdmin")
	@ResponseBody
	
	public ResponseEntity<?> loginAdmin(@RequestBody Administrateur adminRequest) {
	    Map<String, Object> authResult = adminService.login(adminRequest.getEmail(), adminRequest.getMotDePasse());

	    if (authResult != null) {
	        return ResponseEntity.ok(authResult); // Renvoie token + idRole dans un JSON
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou mot de passe incorrect.");
	    }
	}

	  @GetMapping
	    public List<Administrateur> getAllAdmins() {
	        return adminService.getAllAdmins();
	    }
	  @GetMapping("/{id}")
	    public Optional<Administrateur> getAdmin(@PathVariable Long id) {
	        return adminService.getAdminById(id);
	    }
}


	

