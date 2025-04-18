package com.EvalTrack.Services;

import com.EvalTrack.Entities.Module;
import com.EvalTrack.Repositories.ModuleRepository;
import com.EvalTrack.Repositories.SectionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;
    @Autowired
    private SectionRepository  sectionRepository;

    public List<Module> getAllModules() {
        return moduleRepository.findAll();
    }

    public Optional<Module> getModuleById(int id) {
        return moduleRepository.findById(id);
    }

    public Module addModule(Module module) {
    	Module mod = new Module();
    	mod.setMoyenne(module.getMoyenne());
    	mod.setNomModule(module.getNomModule());
    	if (module.getSection() != null) {
            com.EvalTrack.Entities.Section section = sectionRepository.findById(module.getSection().getIdSection()).orElse(null);
            mod.setSection(section);
        } else {
        	mod.setSection(null);
        }

        return moduleRepository.save(module);
    }

    public Module updateModule(int id, Module updatedModule) {
        return moduleRepository.findById(id).map(module -> {
            module.setNomModule(updatedModule.getNomModule());
            module.setMoyenne(updatedModule.getMoyenne());
            if (updatedModule.getSection() != null) {
                com.EvalTrack.Entities.Section section = sectionRepository.findById(module.getSection().getIdSection()).orElse(null);
                module.setSection(section);
            } else {
            	module.setSection(null);
            }
            return moduleRepository.save(module);
        }).orElse(null);
    }

    public void deleteModule(int id) {
        moduleRepository.deleteById(id);
    }
}