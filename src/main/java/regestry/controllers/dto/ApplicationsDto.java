package regestry.controllers.dto;

import java.time.LocalDate;

public record ApplicationsDto(String date,
                              Integer reg_num,
                              Integer creator_id,
                              Integer application_type_id) {
}
