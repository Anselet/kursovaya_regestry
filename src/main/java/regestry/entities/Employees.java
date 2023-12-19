package regestry.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;

@Entity
@Table(name = "employees")
@Getter
@Setter

@RequiredArgsConstructor

@Accessors(chain = true)
public class Employees {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @jakarta.persistence.Id
    @Column(name = "id", nullable = false)
    private Integer Id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "citizen_id", nullable = false)

    private Citizens citizen;

    //public Integer getCitizen(){
    //    return  citizen.getId();
    //}

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "post_id", nullable = false)

    private Post post;

    //public Integer getPost(){
    //    return  post.getId();
    //}

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employees")
    @JsonBackReference
    //@JsonBackReference
    private Set<Acts> acts;

    @JsonBackReference
    @Column(name = "login", nullable = false)
    private String login;

    @JsonBackReference
    @Column(name = "password", nullable = false)
    private String password;
}
