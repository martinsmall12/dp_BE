package cz.maly.dp_be.controller;

import cz.maly.dp_be.exception.ResourceNotFoundException;
import cz.maly.dp_be.model.TypeOfApplication;
import cz.maly.dp_be.repository.TypeOfApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TypeOfApplicationController {

    @Autowired
    TypeOfApplicationRepository typeOfApplicationRepository;

    @CrossOrigin
    @GetMapping("/typeOfApplication")
    public List<TypeOfApplication> getAllTypeOfApplication(HttpServletResponse response) {
        List<TypeOfApplication> typeOfApplications = typeOfApplicationRepository.findAll();
        response.setHeader("Content-Range", String.valueOf(typeOfApplications.size()));
        response.setHeader("Access-Control-Expose-Headers", "Content-Range");
        return typeOfApplicationRepository.findAll();
    }

    @CrossOrigin
    @PostMapping("/typeOfApplication")
    public TypeOfApplication createTypeOfApplication(@Valid @RequestBody TypeOfApplication typeOfApplication) {
        return typeOfApplicationRepository.save(typeOfApplication);
    }

    @CrossOrigin
    @GetMapping("/typeOfApplication/{id}")
    public TypeOfApplication getTypeOfApplicationById(@PathVariable(value = "id") Long typeOfApplicationId) {
        return typeOfApplicationRepository.findById(typeOfApplicationId)
                .orElseThrow(() -> new ResourceNotFoundException("TypeOfApplication", "id", typeOfApplicationId));
    }

/*
    @PutMapping("/notes/{id}")
    public Note updateNote(@PathVariable(value = "id") Long noteId,
                                           @Valid @RequestBody Note noteDetails) {

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        Note updatedNote = noteRepository.save(note);
        return updatedNote;
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        noteRepository.delete(note);

        return ResponseEntity.ok().build();
    }
    */
}
