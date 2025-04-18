package com.EvalTrack.Controllers;

import com.EvalTrack.Entities.Section;
import com.EvalTrack.Services.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sections")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @GetMapping
    public List<Section> getAllSections() {
        return sectionService.getAllSections();
    }

    @GetMapping("/{id}")
    public Optional<Section> getSectionById(@PathVariable int id) {
        return sectionService.getSectionById(id);
    }

    @PostMapping
    public Section createSection(@RequestBody Section section) {
        return sectionService.addSection(section);
    }

    @PutMapping("/{id}")
    public Section updateSection(@PathVariable int id, @RequestBody Section updatedSection) {
        return sectionService.updateSection(id, updatedSection);
    }

    @DeleteMapping("/{id}")
    public void deleteSection(@PathVariable int id) {
        sectionService.deleteSection(id);
    }
}