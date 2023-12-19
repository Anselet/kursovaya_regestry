package regestry.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "passport")
@Getter
@Setter

@RequiredArgsConstructor

@Accessors(chain = true)

public class Passport {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @jakarta.persistence.Id
    @Column(name = "id", nullable = false)
    private Integer Id;

    @Column(name = "subnumber", nullable = false)
    private Integer subnumber;

    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "date", nullable = false)
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate date;

    @Column(name = "division_code", nullable = false, length = 7)
    private String division_code;

    @Column(name = "division_name", nullable = false, length = 50)
    private String division_name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "passport")
    @JsonManagedReference
    //@JsonBackReference
    private Set<Registration> registration;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "citizens_id", nullable = false)

    private Citizens citizen;

//    public Integer getCitizen(){
//        return  citizen.getId();
//    }
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Passport passport = (Passport) o;
        return getId() != null && Objects.equals(getId(), passport.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
