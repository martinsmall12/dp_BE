package cz.maly.dp_be.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Server")
@EntityListeners(AuditingEntityListener.class)
public class Server {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String environment;

    @NotBlank
    private String url;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "server_applicationServer",
            joinColumns = { @JoinColumn(name = "server_id") },
            inverseJoinColumns = { @JoinColumn(name = "applicationServer_id") }
    )
    Set<ApplicationServer> applicationServers = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "server_database",
            joinColumns = { @JoinColumn(name = "server_id") },
            inverseJoinColumns = { @JoinColumn(name = "database_id") }
    )
    Set<Databaze> databasesSer = new HashSet<>();

    @ElementCollection
    @CollectionTable(name="server_applicationServer", joinColumns=@JoinColumn(name="server_id"))
    @Column(name="applicationServer_id")
    private Set<Long> applicationServerIds;

    public Set<Databaze> getDatabasesSer() {
        return databasesSer;
    }

    public void setDatabasesSer(Set<Databaze> databasesSer) {
        this.databasesSer = databasesSer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<ApplicationServer> getApplicationServers() {
        return applicationServers;
    }

    public void setApplicationServers(Set<ApplicationServer> applicationServers) {
        this.applicationServers = applicationServers;
    }

    public Set<Long> getApplicationServerIds() {
        return applicationServerIds;
    }

    public void setApplicationServerIds(Set<Long> applicationServerIds) {
        this.applicationServerIds = applicationServerIds;
    }
}
