package cz.maly.dp_be.controller;

import cz.maly.dp_be.exception.ResourceNotFoundException;
import cz.maly.dp_be.model.TypeDowntime;
import cz.maly.dp_be.repository.TypeDowntimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TypeDowntimeController {

    @Autowired
    TypeDowntimeRepository typeDowntimeRepository;

    @CrossOrigin
    @GetMapping("/typeDowntime")
    public List<TypeDowntime> getAllTypeDowntimes(HttpServletResponse response) {
        List<TypeDowntime> typeDowntimes = typeDowntimeRepository.findAll();
        response.setHeader("Content-Range", String.valueOf(typeDowntimes.size()));
        response.setHeader("Access-Control-Expose-Headers", "Content-Range");
        return typeDowntimes;
    }

    @CrossOrigin
    @PostMapping("/typeDowntime")
    public TypeDowntime createTypeDowntime(@Valid @RequestBody TypeDowntime typeDowntime) {
        return typeDowntimeRepository.save(typeDowntime);
    }

    @CrossOrigin
    @GetMapping("/typeDowntime/{id}")
    public TypeDowntime getTypeDowntimeById(@PathVariable(value = "id") Long typeDowntimeId) {
        return typeDowntimeRepository.findById(typeDowntimeId)
                .orElseThrow(() -> new ResourceNotFoundException("TypeDowntime", "id", typeDowntimeId));
    }

    @CrossOrigin
    @PutMapping("/typeDowntime/{id}")
    public TypeDowntime updateTypeDowntime(@PathVariable(value = "id") Long typeDowntimeId,
                                           @Valid @RequestBody TypeDowntime typeDowntimeDetails) {

        TypeDowntime typeDowntime = typeDowntimeRepository.findById(typeDowntimeId)
                .orElseThrow(() -> new ResourceNotFoundException("TypeDowntime", "id", typeDowntimeId));

        // todo dopsat parametry
        typeDowntime.setName(typeDowntimeDetails.getName());
        //typeDowntime.setEnvironment(typeDowntimeDetails.getEnvironment());

        TypeDowntime updatedTypeDowntime = typeDowntimeRepository.save(typeDowntime);
        return updatedTypeDowntime;
    }

    @CrossOrigin
    @DeleteMapping("/typeDowntime/{id}")
    public ResponseEntity<?> deleteTypeDowntime(@PathVariable(value = "id") Long typeDowntimeId) {
        TypeDowntime typeDowntime = typeDowntimeRepository.findById(typeDowntimeId)
                .orElseThrow(() -> new ResourceNotFoundException("TypeDowntime", "id", typeDowntimeId));

        typeDowntimeRepository.delete(typeDowntime);

        return ResponseEntity.ok().build();
    }

}
