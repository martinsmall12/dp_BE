package cz.maly.dp_be.controller;

import cz.maly.dp_be.exception.ResourceNotFoundException;
import cz.maly.dp_be.model.Databaze;
import cz.maly.dp_be.repository.DatabazeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DatabazeController {

    @Autowired
    DatabazeRepository databazeRepository;

    @CrossOrigin
    @GetMapping("/databaze")
    public List<Databaze> getAllDatabazes(HttpServletResponse response) {
        List<Databaze> databazes = databazeRepository.findAll();
        response.setHeader("Content-Range", String.valueOf(databazes.size()));
        response.setHeader("Access-Control-Expose-Headers", "Content-Range");
        return databazes;
    }

    @CrossOrigin
    @PostMapping("/databaze")
    public Databaze createDatabaze(@Valid @RequestBody Databaze databaze) {
        return databazeRepository.save(databaze);
    }

    @CrossOrigin
    @GetMapping("/databaze/{id}")
    public Databaze getDatabazeById(@PathVariable(value = "id") Long databazeId) {
        return databazeRepository.findById(databazeId)
                .orElseThrow(() -> new ResourceNotFoundException("Databaze", "id", databazeId));
    }

    @CrossOrigin
    @PutMapping("/databaze/{id}")
    public Databaze updateDatabaze(@PathVariable(value = "id") Long databazeId,
                                         @Valid @RequestBody Databaze databazeDetails) {

        Databaze databaze = databazeRepository.findById(databazeId)
                .orElseThrow(() -> new ResourceNotFoundException("Databaze", "id", databazeId));

        // todo dopsat parametry
        databaze.setName(databazeDetails.getName());
        databaze.setEnvironment(databazeDetails.getEnvironment());

        Databaze updatedDatabaze = databazeRepository.save(databaze);
        return updatedDatabaze;
    }

    @CrossOrigin
    @DeleteMapping("/databaze/{id}")
    public ResponseEntity<?> deleteDatabaze(@PathVariable(value = "id") Long databazeId) {
        Databaze databaze = databazeRepository.findById(databazeId)
                .orElseThrow(() -> new ResourceNotFoundException("Databaze", "id", databazeId));

        databazeRepository.delete(databaze);

        return ResponseEntity.ok().build();
    }

}
