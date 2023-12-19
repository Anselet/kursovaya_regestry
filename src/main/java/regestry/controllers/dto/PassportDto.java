package regestry.controllers.dto;

import java.time.LocalDate;

public record PassportDto(
        Integer number,
        Integer subnumber,
        String date,
        String division_code,
        String division_name,
        Integer citizens_id)
  {
}
