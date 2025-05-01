package com.EvalTrack.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.EvalTrack.Entities.Administrateur;
import com.EvalTrack.Entities.Etudiant;
import com.EvalTrack.Entities.Role;
import com.EvalTrack.Repositories.RoleRepository;
import com.EvalTrack.Repositories.UserRepository;
import com.EvalTrack.Security.JwtService;
import com.EvalTrack.Security.PasswordGenerator;

@Service
public class AdministrateurService {

	private final UserRepository userRepository ;
	private final PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;
	private final JwtService jwtS;
    
	@Autowired
	public AdministrateurService(UserRepository userRepository, PasswordEncoder passwordEncode,JwtService jwtS,RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncode;
		this.jwtS=jwtS;
		this.roleRepository=roleRepository;
	}
	//pour ceer un administrateur
	public Administrateur createAdministrateur(Administrateur admin)
	{
		String hashedPassword = passwordEncoder.encode(admin.getMotDePasse());
		admin.setMotDePasse(hashedPassword);
		 if(admin.getRole()!=null)
         {
       	  Role role = roleRepository.findById(admin.getRole().getIdRole()).orElse(null);
       	  admin.setRole(role);
         }else
         {
       	  admin.setRole(null); 
         }
		return userRepository.save(admin);
	}
	//pour connecter au plateforme 
	public Map<String, Object> login(String email, String password) {
	    Optional<Administrateur> optionalAdmin = userRepository.findByEmail(email);

	    if (optionalAdmin.isPresent()) {
	        Administrateur admin = optionalAdmin.get();

	        if (passwordEncoder.matches(password, admin.getMotDePasse())) {
	            String token = jwtS.generateToken(email);
	            Long idRole = admin.getRole().getIdRole();
                Integer idAdmin = admin.getId();
	            Map<String, Object> result = new HashMap<>();
	            result.put("token", token);
	            result.put("idRole", idRole);
	            result.put("idUser", idAdmin);
	            return result;
	        }
	    }
	    return null;
	}
	public Optional<Administrateur> getAdminByEmail(String email) {
		
        return userRepository.findByEmail(email.trim().toLowerCase());
    }
	
	
	public Optional<Administrateur> getAdminById(Long id) {
        return userRepository.findById(id);
    }
	
	public List<Administrateur> getAllAdmins() {
        return userRepository.findAll();
    }
	public String updatePassword(String email) {
	    Optional<Administrateur> adminOpt = userRepository.findByEmail(email);

	    if (adminOpt.isPresent()) {
	    	Administrateur etd = adminOpt.get(); 
	        String newPass = PasswordGenerator.generate();
	        etd.setMotDePasse(passwordEncoder.encode(newPass));
	        userRepository.save(etd);
	        return newPass;
	    } else {
	        throw new RuntimeException("Admin introuvable avec l'email : " + email);
	    }
	}
	

	public void updatePassword(String newPass, String email, String oldPass) {
	    Optional<Administrateur> admin = userRepository.findByEmail(email.trim().toLowerCase());

	    if (admin.isPresent()) {
	        Administrateur e = admin.get();

	        if (passwordEncoder.matches(oldPass, e.getMotDePasse())) {
	            e.setMotDePasse(passwordEncoder.encode(newPass));
	            userRepository.save(e);
	        } else {
	            // Ancien mot de passe incorrect
	            throw new IllegalArgumentException("Ancien mot de passe incorrect.");
	        }
	    } else {
	        // Utilisateur non trouvé
	        throw new IllegalArgumentException("Administrateur non trouvé.");
	    }
	}

}
