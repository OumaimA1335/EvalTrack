package com.EvalTrack.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.EvalTrack.Entities.Examen;
import com.EvalTrack.Services.ExamenService;

@RestController
@RequestMapping("/examen")
@CrossOrigin(origins = "http://localhost:8080")
public class ExamenController {

    private final ExamenService examService;

    @Autowired
    public ExamenController(ExamenService examService) {
        this.examService = examService;
    }

    @GetMapping
    public List<Examen> getAllExams() {
        return examService.getAllExams();
    }

    @GetMapping("/{id}")
    public Examen getExamById(@PathVariable int id) {
        return examService.getExamById(id);
    }

    @GetMapping("/student/{id}")
    public List<Examen> getExamByStudentId(@PathVariable int id) {
        return examService.findByStudent(id);
    }

    @GetMapping("/subject/{id}")
    public List<Examen> getExamBySubjectId(@PathVariable int id) {
        return examService.findBySubject(id);
    }

    @GetMapping("/type/{type}")
    public List<Examen> getExamByType(@PathVariable String type) {
        return examService.findByType(type);
    }

    @PostMapping
    public Examen createExam(@RequestBody Examen examen) {
        return examService.saveExam(examen);
    }

    @PutMapping("/{id}")
    public Examen updateExam(@PathVariable int id, @RequestBody Examen updatedExam) {
        return examService.updateExam(id, updatedExam);
    }

    @DeleteMapping("/{id}")
    public void deleteExam(@PathVariable int id) {
        examService.deleteExam(id);
    }
}
