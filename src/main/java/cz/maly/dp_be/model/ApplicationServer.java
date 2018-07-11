package cz.maly.dp_be.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "applicationServer")
@EntityListeners(AuditingEntityListener.class)
public class ApplicationServer {
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
            },
            mappedBy = "applicationServers")
    @JsonIgnore
    private Set<Server> servers = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "applicationServer_application",
            joinColumns = { @JoinColumn(name = "applicationServer_id") },
            inverseJoinColumns = { @JoinColumn(name = "application_id") }
    )
    Set<Application> applications = new HashSet<>();

    @ElementCollection
    @CollectionTable(name="applicationServer_application", joinColumns=@JoinColumn(name="applicationServer_id"))
    @Column(name="application_id")
    private Set<Long> applicationsIds;

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

    public Set<Server> getServers() {
        return servers;
    }

    public void setServers(Set<Server> servers) {
        this.servers = servers;
    }

    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }

    public Set<Long> getApplicationsIds() {
        return applicationsIds;
    }

    public void setApplicationsIds(Set<Long> applicationsIds) {
        this.applicationsIds = applicationsIds;
    }
}
