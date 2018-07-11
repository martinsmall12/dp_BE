package cz.maly.dp_be.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "downtime")
@EntityListeners(AuditingEntityListener.class)
public class Downtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

   @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "downtimes")
   @JsonIgnore
    private Set<Application> applications = new HashSet<>();

    @ElementCollection
    @CollectionTable(name="application_downtime", joinColumns=@JoinColumn(name="downtime_id"))
    @Column(name="application_id")
    private Set<Long> applicationIds;

    @OneToOne
    @JoinColumn(name = "typeDowntime_id")
    private TypeDowntime typeDowntime;

    private Date validFrom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }

    public TypeDowntime getTypeDowntime() {
        return typeDowntime;
    }

    public void setTypeDowntime(TypeDowntime typeDowntime) {
        this.typeDowntime = typeDowntime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Set<Long> getApplicationIds() {
        return applicationIds;
    }

    public void setApplicationIds(Set<Long> applicationIds) {
        this.applicationIds = applicationIds;
    }


}
