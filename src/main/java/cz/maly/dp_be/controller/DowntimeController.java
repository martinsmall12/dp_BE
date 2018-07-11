package cz.maly.dp_be.controller;

import cz.maly.dp_be.exception.ResourceNotFoundException;
import cz.maly.dp_be.model.Downtime;
import cz.maly.dp_be.repository.DowntimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DowntimeController {

    @Autowired
    DowntimeRepository downtimeRepository;

    @CrossOrigin
    @GetMapping("/downtime")
    public List<Downtime> getAllDowntimes(HttpServletResponse response) {
        List<Downtime> downtimes = downtimeRepository.findAll();
        response.setHeader("Content-Range", String.valueOf(downtimes.size()));
        response.setHeader("Access-Control-Expose-Headers", "Content-Range");
        return downtimes;
    }

    @CrossOrigin
    @PostMapping("/downtime")
    public Downtime createDowntime(@Valid @RequestBody Downtime downtime) {
        return downtimeRepository.save(downtime);
    }

    @CrossOrigin
    @GetMapping("/downtime/{id}")
    public Downtime getDowntimeById(@PathVariable(value = "id") Long downtimeId) {
        return downtimeRepository.findById(downtimeId)
                .orElseThrow(() -> new ResourceNotFoundException("Downtime", "id", downtimeId));
    }

    @CrossOrigin
    @PutMapping("/downtime/{id}")
    public Downtime updateDowntime(@PathVariable(value = "id") Long downtimeId,
                                           @Valid @RequestBody Downtime downtimeDetails) {

        Downtime downtime = downtimeRepository.findById(downtimeId)
                .orElseThrow(() -> new ResourceNotFoundException("Downtime", "id", downtimeId));

        // todo dopsat parametry
        downtime.setApplications(downtimeDetails.getApplications());
        //downtime.setEnvironment(downtimeDetails.getEnvironment());

        Downtime updatedDowntime = downtimeRepository.save(downtime);
        return updatedDowntime;
    }

    @CrossOrigin
    @DeleteMapping("/downtime/{id}")
    public ResponseEntity<?> deleteDowntime(@PathVariable(value = "id") Long downtimeId) {
        Downtime downtime = downtimeRepository.findById(downtimeId)
                .orElseThrow(() -> new ResourceNotFoundException("Downtime", "id", downtimeId));

        downtimeRepository.delete(downtime);

        return ResponseEntity.ok().build();
    }

}
