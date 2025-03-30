package com.EvalTrack.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.EvalTrack.Entities.Administrateur;
import com.EvalTrack.Repositories.UserRepository;
import com.EvalTrack.Security.JwtService;

@Service
public class AdministrateurService {

	private final UserRepository userRepository ;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtS;
    
	@Autowired
	public AdministrateurService(UserRepository userRepository, PasswordEncoder passwordEncode,JwtService jwtS) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncode;
		this.jwtS=jwtS;
	}
	//pour ceer un administrateur
	public Administrateur createAdministrateur(Administrateur admin)
	{
		String hashedPassword = passwordEncoder.encode(admin.getMotDePasse());
		admin.setMotDePasse(hashedPassword);
		return userRepository.save(admin);
	}
	//pour connecter au plateforme 
	public String login(String email, String password)
	{
		if(userRepository.findByEmail(email) != null)
		{
			Administrateur admin = userRepository.findByEmail(email).get();
			//on compare le mot de passe fournie par celui haché
			if (admin != null && passwordEncoder.matches(password, admin.getMotDePasse())) {
	            return jwtS.generateToken(email); // Générer un JWT
	        }
		}
		return null;
	}
	
	
	public Optional<Administrateur> getAdminById(Long id) {
        return userRepository.findById(id);
    }
	
	public List<Administrateur> getAllAdmins() {
        return userRepository.findAll();
    }
}
