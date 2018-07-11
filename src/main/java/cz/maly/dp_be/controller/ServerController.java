package cz.maly.dp_be.controller;

import cz.maly.dp_be.exception.ResourceNotFoundException;
import cz.maly.dp_be.model.Server;
import cz.maly.dp_be.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ServerController {

    @Autowired
    ServerRepository serverRepository;

    @CrossOrigin
    @GetMapping("/server")
    public List<Server> getAllServers(HttpServletResponse response) {
        List<Server> servers = serverRepository.findAll();
        response.setHeader("Content-Range", String.valueOf(servers.size()));
        response.setHeader("Access-Control-Expose-Headers", "Content-Range");
        return servers;
    }

    @CrossOrigin
    @PostMapping("/server")
    public Server createServer(@Valid @RequestBody Server server) {
        return serverRepository.save(server);
    }

    @CrossOrigin
    @GetMapping("/server/{id}")
    public Server getServerById(@PathVariable(value = "id") Long serverId) {
        return serverRepository.findById(serverId)
                .orElseThrow(() -> new ResourceNotFoundException("Server", "id", serverId));
    }

    @CrossOrigin
    @PutMapping("/server/{id}")
    public Server updateServer(@PathVariable(value = "id") Long serverId,
                                         @Valid @RequestBody Server serverDetails) {

        Server server = serverRepository.findById(serverId)
                .orElseThrow(() -> new ResourceNotFoundException("Server", "id", serverId));

        // todo dopsat parametry
        server.setName(serverDetails.getName());
        server.setEnvironment(serverDetails.getEnvironment());

        Server updatedServer = serverRepository.save(server);
        return updatedServer;
    }

    @CrossOrigin
    @DeleteMapping("/server/{id}")
    public ResponseEntity<?> deleteServer(@PathVariable(value = "id") Long serverId) {
        Server server = serverRepository.findById(serverId)
                .orElseThrow(() -> new ResourceNotFoundException("Server", "id", serverId));

        serverRepository.delete(server);

        return ResponseEntity.ok().build();
    }

}
