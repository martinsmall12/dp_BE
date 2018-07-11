package cz.maly.dp_be.controller;

import cz.maly.dp_be.exception.ResourceNotFoundException;
import cz.maly.dp_be.model.Application;
import cz.maly.dp_be.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApplicationController {

    @Autowired
    ApplicationRepository applicationRepository;

    @CrossOrigin
    @GetMapping("/application")
    public List<Application> getAllApplications(HttpServletResponse response) {
        List<Application> applications = applicationRepository.findAll();
        response.setHeader("Content-Range", String.valueOf(applications.size()));
        response.setHeader("Access-Control-Expose-Headers", "Content-Range");
        return applications;
    }

    @CrossOrigin
    @PostMapping("/application")
    public Application createApplication(@Valid @RequestBody Application application) {
        return applicationRepository.save(application);
    }

    @CrossOrigin
    @GetMapping("/application/{id}")
    public Application getApplicationById(@PathVariable(value = "id") Long applicationId) {
        return applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Application", "id", applicationId));
    }

    @CrossOrigin
    @PutMapping("/application/{id}")
    public Application updateApplication(@PathVariable(value = "id") Long applicationId,
                                         @Valid @RequestBody Application applicationDetails) {

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Application", "id", applicationId));

        // todo dopsat parametry
        application.setName(applicationDetails.getName());
        application.setEnvironment(applicationDetails.getEnvironment());

        Application updatedApplication = applicationRepository.save(application);
        return updatedApplication;
    }

    @CrossOrigin
    @DeleteMapping("/application/{id}")
    public ResponseEntity<?> deleteApplication(@PathVariable(value = "id") Long applicationId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Application", "id", applicationId));

        applicationRepository.delete(application);

        return ResponseEntity.ok().build();
    }

}
