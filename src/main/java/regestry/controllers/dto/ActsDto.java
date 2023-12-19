package regestry.controllers.dto;

import java.time.LocalDate;

public record ActsDto(
        String date_of_registration,
        String reg_company_name,
        Integer employee_id,
        Integer act_type_id)
  {
}
