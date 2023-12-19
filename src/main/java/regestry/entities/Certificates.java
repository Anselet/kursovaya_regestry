package regestry.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "certificates")
@Getter
@Setter
@RequiredArgsConstructor

@Accessors(fluent = false, chain = true)
public class Certificates {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @jakarta.persistence.Id
    @Column(name = "id", nullable = false)
    private Integer Id;

    @Column(name = "subnumber", nullable = false)
    private Integer subnumber;

    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "date_of_issue", nullable = false)
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate date_of_issue;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "reg_number", nullable = false)

    private Acts acts;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cert_type_id", nullable = false)

    private CertificateType certificateType;

    public Integer getActs(){
        return acts.getRegNumber();
    }


    @ManyToMany (cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "cert_citizens",
            joinColumns = @JoinColumn(name = "cert_id"),
            inverseJoinColumns = @JoinColumn(name = "citizen_id")
    )
    private Set<Citizens> recipients;
}
