package com.EvalTrack.Controllers;

import com.EvalTrack.Entities.Module;
import com.EvalTrack.Services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/modules")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @GetMapping
    public List<Module> getAllModules() {
        return moduleService.getAllModules();
    }

    @GetMapping("/{id}")
    public Optional<Module> getModuleById(@PathVariable int id) {
        return moduleService.getModuleById(id);
    }

    @PostMapping
    public Module createModule(@RequestBody Module module) {
        return moduleService.addModule(module);
    }

    @PutMapping("/{id}")
    public Module updateModule(@PathVariable int id, @RequestBody Module updatedModule) {
        return moduleService.updateModule(id, updatedModule);
    }

    @DeleteMapping("/{id}")
    public void deleteModule(@PathVariable int id) {
        moduleService.deleteModule(id);
    }
}