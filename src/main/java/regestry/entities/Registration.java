package regestry.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Entity
@Table(name = "registration")
@Getter
@Setter

@RequiredArgsConstructor

@Accessors(chain = true)
public class Registration {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @jakarta.persistence.Id
    @Column(name = "id", nullable = false)
    private Integer Id;

    @Column(name = "date", nullable = false)
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate date;

    @Column(name = "division_name", nullable = false, length = 50)
    private String division_name;

    @Column(name = "division_code", nullable = false, length = 7)
    private String division_code;

    @Column(name = "house_number", nullable = false, length = 5)
    private String house_number;

    @Column(name = "flat_number", nullable = false)
    private Integer flat_number;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "passport_id", nullable = false)

    private Passport passport;
    public Integer getPassport() {
        return passport.getId();
    }


    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "regions", nullable = false)
    private String regions;

    @Column(name = "subjects", nullable = false)
    private String subjects;

    @Column(name = "districts", nullable = false)
    private String districts;

    @Column(name = "population_center", nullable = false)
    private String population_center;

    @Column(name = "streets", nullable = false)
    private String streets;

    @Column(name = "cancellation_date", nullable = false)
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate cancellation_date;
}
