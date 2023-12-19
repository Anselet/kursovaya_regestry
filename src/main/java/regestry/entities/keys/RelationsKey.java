package regestry.entities.keys;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;
import regestry.entities.Citizens;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RelationsKey implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "parent_id", nullable = false)
    @JsonManagedReference
    private Citizens parent;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "child_id", nullable = false)
    @JsonManagedReference
    private Citizens child;

//    public Integer getChild(){
//        return child.getId();
//    }
//
//    public Integer getParent(){
//        return parent.getId();
//    }

}
