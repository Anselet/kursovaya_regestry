package regestry.controllers.dto;

import java.time.LocalDate;

public record RegistrationDto(
        String date,
        String division_code,
        String division_name,
        String house_number,
        Integer flat_number,
        Integer passport_id,
        String state,
        String regions,
        String subjects,
        String districts,
        String population_center,
        String streets,
        String cancellation_date)
  {
}
