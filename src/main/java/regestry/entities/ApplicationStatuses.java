package regestry.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import regestry.entities.keys.AppStatusesKey;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "application_statuses")
@Getter
@Setter

@RequiredArgsConstructor

@Accessors(chain = true)
public class ApplicationStatuses {

    @EmbeddedId
    private AppStatusesKey pk;

    @ManyToOne
    @JoinColumn(name = "employees_id")
    private Employees employee;

    @Column(name = "date_of_change", nullable = false)
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd.MM.yyyy HH:mm:ss")
    //@DateTimeFormat(pattern="dd.MM.yyyy HH:mm:ss")
    private LocalDateTime changeDate;
}
