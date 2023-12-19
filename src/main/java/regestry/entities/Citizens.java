package regestry.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "citizens")
@Getter
@Setter
@RequiredArgsConstructor

@Accessors(fluent = false, chain = true)
public class Citizens {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @jakarta.persistence.Id
    @Column(name = "id", nullable = false)
    private Integer Id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    @Column(name = "patronymic", nullable = false, length = 50)
    private String patronymic;


    @JsonFormat(pattern = "dd.MM.yyyy")
    @Column(name = "birth_day", nullable = false)
    private LocalDate birthDay;

    @Column(name = "birth_place", nullable = false, length = 50)
    private String birthPlace;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "citizen")
    @JsonBackReference
    private Set<Passport> passports;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "citizen")
    @JsonBackReference
    private Set<Contacts> contacts;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pk.parent")
    @JsonBackReference
    private  Set<Relations> children;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pk.child")
    @JsonBackReference
    private  Set<Relations> parents;


    @ManyToMany(mappedBy = "involved")
    @JsonBackReference
    private Set<Acts> acts;

    @ManyToMany(mappedBy = "applicants")
    @JsonBackReference
    private Set<Applications> apps;

//    public Set<Relations> getParents(){
//        for(Relations rel : parents){
//            rel.getPk().setParent(null);
//            rel.getPk().setChild(null);
//            }
//        return parents;
//    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "citizen")
    @JsonBackReference
    private Set<Employees> employees;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "citizen")
    @JsonBackReference
    private Set<Applications> applications;

    @ManyToMany(mappedBy = "recipients")
    @JsonBackReference
    private Set<Certificates> certificates;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Citizens citizens = (Citizens) o;
        return getId() != null && Objects.equals(getId(), citizens.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
