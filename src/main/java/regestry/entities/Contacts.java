package regestry.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "contacts")
@Getter
@Setter

@RequiredArgsConstructor

@Accessors(chain = true)

public class Contacts {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @jakarta.persistence.Id
    @Column(name = "id", nullable = false)
    private Integer Id;

    @Column(name = "phone_number", nullable = false)
    private Long phone_number;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "real_address", nullable = false)
    private String real_address;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "citizens_id", nullable = false)

    private Citizens citizen;
    public Integer getCitizen(){
        return  citizen.getId();
    }
}
