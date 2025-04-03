package com.EvalTrack.Services;

import com.EvalTrack.Entities.Module;
import com.EvalTrack.Repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    public List<Module> getAllModules() {
        return moduleRepository.findAll();
    }

    public Optional<Module> getModuleById(int id) {
        return moduleRepository.findById(id);
    }

    public Module addModule(Module module) {
        return moduleRepository.save(module);
    }

    public Module updateModule(int id, Module updatedModule) {
        return moduleRepository.findById(id).map(module -> {
            module.setNomModule(updatedModule.getNomModule());
            module.setMoyenne(updatedModule.getMoyenne());
            return moduleRepository.save(module);
        }).orElse(null);
    }

    public void deleteModule(int id) {
        moduleRepository.deleteById(id);
    }
}