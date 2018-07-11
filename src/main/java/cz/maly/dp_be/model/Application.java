package cz.maly.dp_be.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Blob;
import java.util.*;

@Entity
@Table(name = "application")
@EntityListeners(AuditingEntityListener.class)
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String environment;

    @NotBlank
    private String version;

    private String description;

    private Date validityFrom;

    private Date validityTo;

    private String pathToSource;

    private Blob sourceCode;

    @OneToOne
    @JoinColumn(name = "typeOfApplication_id")
    private TypeOfApplication typeOfApplication;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "application_author",
            joinColumns = { @JoinColumn(name = "application_id") },
            inverseJoinColumns = { @JoinColumn(name = "author_id") }
    )
    Set<Author> authors = new HashSet<>();

    @ElementCollection
    @CollectionTable(name="application_author", joinColumns=@JoinColumn(name="application_id"))
    @Column(name="author_id")
    private Set<Long> authorIds;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "application_downtime",
            joinColumns = { @JoinColumn(name = "application_id") },
            inverseJoinColumns = { @JoinColumn(name = "downtime_id") }
    )
    Set<Downtime> downtimes = new HashSet<>();

    @ElementCollection
    @CollectionTable(name="application_downtime", joinColumns=@JoinColumn(name="application_id"))
    @Column(name="downtime_id")
    private Set<Long> downtimeIds;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "applications")
    @JsonIgnore
    private Set<Employee> employees = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "applications")
    @JsonIgnore
    private Set<ApplicationServer> applicationServers = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "application_database",
            joinColumns = { @JoinColumn(name = "application_id") },
            inverseJoinColumns = { @JoinColumn(name = "database_id") }
    )
    Set<Databaze> databasesApp = new HashSet<>();

    @ElementCollection
    @CollectionTable(name="application_database", joinColumns=@JoinColumn(name="application_id"))
    @Column(name="database_id")
    private Set<Long> databaseIds;

    public Set<Databaze> getDatabasesApp() {
        return databasesApp;
    }

    public void setDatabasesApp(Set<Databaze> databasesApp) {
        this.databasesApp = databasesApp;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getValidityFrom() {
        return validityFrom;
    }

    public void setValidityFrom(Date validityFrom) {
        this.validityFrom = validityFrom;
    }

    public Date getValidityTo() {
        return validityTo;
    }

    public void setValidityTo(Date validityTo) {
        this.validityTo = validityTo;
    }

    public String getPathToSource() {
        return pathToSource;
    }

    public void setPathToSource(String pathToSource) {
        this.pathToSource = pathToSource;
    }

    public Blob getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(Blob sourceCode) {
        this.sourceCode = sourceCode;
    }

    public TypeOfApplication getTypeOfApplication() {
        return typeOfApplication;
    }

    public void setTypeOfApplication(TypeOfApplication typeOfApplication) {
        this.typeOfApplication = typeOfApplication;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<Long> getAuthorIds() {
        return authorIds;
    }

    public void setAuthorIds(Set<Long> authorIds) {
        this.authorIds = authorIds;
    }

    public Set<Downtime> getDowntimes() {
        return downtimes;
    }

    public void setDowntimes(Set<Downtime> downtimes) {
        this.downtimes = downtimes;
    }

    public Set<Long> getDowntimeIds() {
        return downtimeIds;
    }

    public void setDowntimeIds(Set<Long> downtimeIds) {
        this.downtimeIds = downtimeIds;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<ApplicationServer> getApplicationServers() {
        return applicationServers;
    }

    public void setApplicationServers(Set<ApplicationServer> applicationServers) {
        this.applicationServers = applicationServers;
    }

    public Set<Long> getDatabaseIds() {
        return databaseIds;
    }

    public void setDatabaseIds(Set<Long> databaseIds) {
        this.databaseIds = databaseIds;
    }
}
