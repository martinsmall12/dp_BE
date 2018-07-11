package cz.maly.dp_be.controller;

import cz.maly.dp_be.exception.ResourceNotFoundException;
import cz.maly.dp_be.model.ApplicationServer;
import cz.maly.dp_be.repository.ApplicationServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApplicationServerController {

    @Autowired
    ApplicationServerRepository applicationServerRepository;

    @CrossOrigin
    @GetMapping("/applicationServer")
    public List<ApplicationServer> getAllApplicationServers(HttpServletResponse response) {
        List<ApplicationServer> applicationServers = applicationServerRepository.findAll();
        response.setHeader("Content-Range", String.valueOf(applicationServers.size()));
        response.setHeader("Access-Control-Expose-Headers", "Content-Range");
        return applicationServers;
    }

    @CrossOrigin
    @PostMapping("/applicationServer")
    public ApplicationServer createApplicationServer(@Valid @RequestBody ApplicationServer applicationServer) {
        return applicationServerRepository.save(applicationServer);
    }

    @CrossOrigin
    @GetMapping("/applicationServer/{id}")
    public ApplicationServer getApplicationServerById(@PathVariable(value = "id") Long applicationServerId) {
        return applicationServerRepository.findById(applicationServerId)
                .orElseThrow(() -> new ResourceNotFoundException("ApplicationServer", "id", applicationServerId));
    }

    @CrossOrigin
    @PutMapping("/applicationServer/{id}")
    public ApplicationServer updateApplicationServer(@PathVariable(value = "id") Long applicationServerId,
                                         @Valid @RequestBody ApplicationServer applicationServerDetails) {

        ApplicationServer applicationServer = applicationServerRepository.findById(applicationServerId)
                .orElseThrow(() -> new ResourceNotFoundException("ApplicationServer", "id", applicationServerId));

        // todo dopsat parametry
        applicationServer.setName(applicationServerDetails.getName());
        applicationServer.setEnvironment(applicationServerDetails.getEnvironment());

        ApplicationServer updatedApplicationServer = applicationServerRepository.save(applicationServer);
        return updatedApplicationServer;
    }

    @CrossOrigin
    @DeleteMapping("/applicationServer/{id}")
    public ResponseEntity<?> deleteApplicationServer(@PathVariable(value = "id") Long applicationServerId) {
        ApplicationServer applicationServer = applicationServerRepository.findById(applicationServerId)
                .orElseThrow(() -> new ResourceNotFoundException("ApplicationServer", "id", applicationServerId));

        applicationServerRepository.delete(applicationServer);

        return ResponseEntity.ok().build();
    }

}
