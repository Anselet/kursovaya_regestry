package regestry.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "applications")
@Getter
@Setter

@RequiredArgsConstructor
@Accessors(chain = true)
public class Applications {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "date", nullable = false)
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "application_type_id", nullable = false)

    private ApplicationType applicationType;

    public Integer getApplicationType() {
        return applicationType.getId();
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "creator_id", nullable = false)

    private Citizens citizen;

    //public Integer getCitizens() {
    //    return citizen.getId();
    //}

    //@JsonBackReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reg_num", referencedColumnName = "reg_number")
    private Acts acts;

    @ManyToMany (cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JsonBackReference
    @JoinTable(name = "app_citizens",
            joinColumns = @JoinColumn(name = "app_id"),
            inverseJoinColumns = @JoinColumn(name = "citizen_id")
    )
    private Set<Citizens> applicants;

}
