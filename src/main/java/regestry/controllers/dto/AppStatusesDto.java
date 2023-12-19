package regestry.controllers.dto;

import java.time.LocalDateTime;

public record AppStatusesDto(
        Integer application,
        Integer employee,
        Integer status,
        String changeDate
) {
}
