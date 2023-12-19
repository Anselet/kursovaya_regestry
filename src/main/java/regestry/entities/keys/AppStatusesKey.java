package regestry.entities.keys;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import regestry.entities.ApplicationStatus;
import regestry.entities.Applications;
import regestry.entities.Citizens;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppStatusesKey implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "application_id", nullable = false)
    @JsonManagedReference
    private Applications application;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "status_id", nullable = false)
    @JsonManagedReference
    private ApplicationStatus status;
}
