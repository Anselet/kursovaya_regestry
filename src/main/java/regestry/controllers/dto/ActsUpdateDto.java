package regestry.controllers.dto;

import java.time.LocalDate;

public record ActsUpdateDto(
        Integer actType,
        String date_of_registration,
        Integer employees,
        Integer regNumber,
        String reg_company_name
) {
}
