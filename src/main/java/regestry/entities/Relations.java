package regestry.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import regestry.entities.keys.RelationsKey;

@Entity
@Table(name = "relations")
@Getter
@Setter

@RequiredArgsConstructor

@Accessors(chain = true)

public class Relations {
    @EmbeddedId
    private RelationsKey pk;

    @ManyToOne
    @JoinColumn(name = "relation_id")
    private RelationType relationType;
}
