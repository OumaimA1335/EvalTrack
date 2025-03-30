package com.EvalTrack.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.EvalTrack.Entities.Administrateur;
import com.EvalTrack.Entities.Etudiant;
import com.EvalTrack.Entities.Examen;
import com.EvalTrack.Repositories.EtudiantRepository;
import com.EvalTrack.Security.JwtService;

@Service
public class EtudiantService {
	private final EtudiantRepository etudiantRepository ;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtS;
	@Autowired
	public EtudiantService(EtudiantRepository etudiantRepository, PasswordEncoder passwordEncode,JwtService jwtS) {
		this.etudiantRepository = etudiantRepository;
		this.passwordEncoder = passwordEncode;
		this.jwtS=jwtS;
	}
	
	//pour ceer un etudiant
		public Etudiant createEtudiant(Etudiant ETD)
		{
			String hashedPassword = passwordEncoder.encode(ETD.getMotDePasse());
			ETD.setMotDePasse(hashedPassword);
			return etudiantRepository.save(ETD);
		}
		
		
		//pour connecter au plateforme 
		public String login(String email, String password)
		{
			if(etudiantRepository.findByEmail(email) != null)
			{
				Etudiant admin = etudiantRepository.findByEmail(email).get();
				//on compare le mot de passe fournie par celui haché
				if (admin != null && passwordEncoder.matches(password, admin.getMotDePasse())) {
		            return jwtS.generateToken(email); // Générer un JWT
		        }
			}
			return null;
		}
		
		public Etudiant modifierEtudiant(Long id, Etudiant newData) {
	        return etudiantRepository.findById(id)
	            .map(etudiant -> {
	               
	                etudiant.setEmail(newData.getEmail());
	                
	                // Vérifier si un nouveau mot de passe est fourni et le crypter
	                if (newData.getMotDePasse() != null && !newData.getMotDePasse().isEmpty()) {
	                    etudiant.setMotDePasse(passwordEncoder.encode(newData.getMotDePasse()));
	                }
	                if (newData.getSection() != null) {
	                    etudiant.setSection(newData.getSection());
	                }
	                etudiant.setNiveau(newData.getNiveau());
	                return etudiantRepository.save(etudiant);
	            })
	            .orElseThrow(() -> new RuntimeException("Étudiant non trouvé avec Id: " +id));
	    }
		public List<Etudiant> getAllStudents() {
	        return etudiantRepository.findAll();
	    }
		public Optional<Etudiant> getEtudiantById(Long id) {
	        return etudiantRepository.findById(id);
	    }
		 
}
