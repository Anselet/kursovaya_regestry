package regestry.controllers.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ApplicationUpdateDto(
        Integer acts,
        Integer applicationType,
        Integer citizen,
        String date,
        Integer id) {
}
