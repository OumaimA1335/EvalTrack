package com.EvalTrack.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.EvalTrack.Entities.Administrateur;
import com.EvalTrack.Entities.Etudiant;
import com.EvalTrack.Entities.Examen;
import com.EvalTrack.Entities.Matiére;
import com.EvalTrack.Entities.Reclamation;
import com.EvalTrack.Entities.Role;
import com.EvalTrack.Entities.Section;
import com.EvalTrack.Repositories.EtudiantRepository;
import com.EvalTrack.Repositories.ReclamationRepository;
import com.EvalTrack.Repositories.RoleRepository;
import com.EvalTrack.Repositories.SectionRepository;
import com.EvalTrack.Repositories.UserRepository;
import com.EvalTrack.Security.JwtService;
import com.EvalTrack.Security.PasswordGenerator;

@Service
public class EtudiantService {
    
    private final EtudiantRepository etudiantRepository;
    private final SectionRepository sectionRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ReclamationRepository reclamationRepository;
    private final JwtService jwtService;
    private final UserRepository administrateurRepository;

    @Autowired
    public EtudiantService(JwtService jwtService, EtudiantRepository etudiantRepository, 
                           SectionRepository sectionRepository, PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository, ReclamationRepository reclamationRepository,
                           UserRepository administrateurRepository) {
        this.etudiantRepository = etudiantRepository;
        this.sectionRepository = sectionRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.reclamationRepository = reclamationRepository;
        this.administrateurRepository = administrateurRepository;
    }

    // Pour créer un étudiant
    public Etudiant createEtudiant(Etudiant etudiant) {
        String hashedPassword = passwordEncoder.encode(etudiant.getMotDePasse());
        etudiant.setMotDePasse(hashedPassword);

        // Récupérer la matière si l'ID est fourni
        if (etudiant.getSection() != null) {
            Section section = sectionRepository.findById(etudiant.getSection().getIdSection()).orElse(null);
            etudiant.setSection(section);
        } else {
            etudiant.setSection(null);
        }

        if (etudiant.getRole() != null) {
            Role role = roleRepository.findById(etudiant.getRole().getIdRole()).orElse(null);
            etudiant.setRole(role);
        } else {
            etudiant.setRole(null);
        }

        return etudiantRepository.save(etudiant);
    }

    // Pour connecter un étudiant à la plateforme
    public Map<String, Object> login(String email, String password) {
        Optional<Etudiant> optionalEtudiant = etudiantRepository.findByEmail(email);

        if (optionalEtudiant.isPresent()) {
            Etudiant etudiant = optionalEtudiant.get();

            if (passwordEncoder.matches(password, etudiant.getMotDePasse())) {
                String token = jwtService.generateToken(email);
                Long idRole = etudiant.getRole().getIdRole();
                Long idEtudiant = etudiant.getId();

                Map<String, Object> result = new HashMap<>();
                result.put("token", token);
                result.put("idRole", idRole);
                result.put("idUser", idEtudiant);
                return result;
            }
        }
        return null;  // Si l'étudiant n'existe pas ou que le mot de passe ne correspond pas
    }

    // Pour modifier les informations d'un étudiant
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
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé avec Id: " + id));
    }

    // Récupérer tous les étudiants
    public List<Etudiant> getAllStudents() {
        return etudiantRepository.findAll();
    }

    // Récupérer un étudiant par son ID
    public Optional<Etudiant> getEtudiantById(Long id) {
        return etudiantRepository.findById(id);
    }

    // Récupérer les étudiants par section et niveau
    public List<Etudiant> getEtudiantsBySectionAndNiveau(Long sectionId, int niveau) {
        return etudiantRepository.findBySection_SectionIdAndNiveau(sectionId, niveau);
    }

    // Récupérer un étudiant par son email
    public Optional<Etudiant> getEtudiantByEmail(String email) {
        return etudiantRepository.findByEmail(email.trim().toLowerCase());
    }

    // Mettre à jour le mot de passe de l'étudiant
    public String updatePassword(String email) {
        Optional<Etudiant> etdOpt = etudiantRepository.findByEmail(email);

        if (etdOpt.isPresent()) {
            Etudiant etd = etdOpt.get();
            String newPass = PasswordGenerator.generate();
            etd.setMotDePasse(passwordEncoder.encode(newPass));
            etudiantRepository.save(etd);
            return newPass;
        } else {
            throw new RuntimeException("Étudiant introuvable avec l'email : " + email);
        }
    }

    // Pour changer le mot de passe de l'étudiant
    public void updatePassword(String newPass, String email, String oldPass) {
        Optional<Etudiant> etudiant = etudiantRepository.findByEmail(email.trim().toLowerCase());

        if (etudiant.isPresent()) {
            Etudiant e = etudiant.get();

            if (passwordEncoder.matches(oldPass, e.getMotDePasse())) {
                e.setMotDePasse(passwordEncoder.encode(newPass));
                etudiantRepository.save(e);
            }
        }
    }

    // Récupérer un étudiant par son CIN
    public Optional<Etudiant> findEtudiantByCin(Integer cin) {
        return etudiantRepository.findByCin(cin);
    }

    // Ajouter une réclamation pour un étudiant
    public Reclamation addReclamation(Long etudiantId, Reclamation reclamation) {
        Optional<Etudiant> etudiantOpt = etudiantRepository.findById(etudiantId);
        if (etudiantOpt.isPresent()) {
            Etudiant etudiant = etudiantOpt.get();
            reclamation.setEtudiant(etudiant); // Lier la réclamation à l'étudiant

            return reclamationRepository.save(reclamation);
        }
        return null;  // Si l'étudiant n'existe pas
    }

    // Récupérer un utilisateur par son ID et son rôle
    public Optional<?> getUserByIdAndRole(Long idUser, int idRole) {
        if (idRole == 2) { // 1 = étudiant
            return etudiantRepository.findById(idUser);
        } else if (idRole == 1) { // 2 = administrateur
            return administrateurRepository.findById(idUser);
        } else {
            return Optional.empty(); // Rôle inconnu
        }
    }
}
