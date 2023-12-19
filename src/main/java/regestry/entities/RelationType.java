package regestry.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

@Entity
@Table(name = "relation_type")
@Data
@Accessors(chain = true)
public class RelationType {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "relationType")
    @JsonBackReference
    private Set<Relations> relations;
}
