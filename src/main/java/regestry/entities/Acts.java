package regestry.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "acts")
@Getter
@Setter

@RequiredArgsConstructor

@Accessors(chain = true)
public class Acts {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "reg_number", nullable = false)
    private Integer regNumber;

    @Column(name = "reg_company_name", nullable = false)
    private String reg_company_name;

    @Column(name = "date_of_registration", nullable = false)
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate date_of_registration;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "employee_id", nullable = false)

    private Employees employees;

    public Integer getEmployees(){
        return  employees.getId();
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "act_type_id", nullable = false)

    private ActType actType;

    @ManyToMany (cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "act_citizens",
            joinColumns = @JoinColumn(name = "act_id"),
            inverseJoinColumns = @JoinColumn(name = "citizen_id")
    )
    private Set<Citizens> involved;

    public void addInvolved(Citizens person){
        this.involved.add(person);
        person.getActs().add(this);
    }
    public void removeInvolved(Citizens person){
        this.involved.remove(person);
        person.getActs().remove(this);
    }

    public Integer getActType(){
        return  actType.getId();
    }

    @OneToOne(mappedBy = "acts")
    @JsonBackReference
    private Applications applications;
}
