package regestry.controllers.dto;

import java.time.LocalDate;

public record CitizenUpdateDto(
        String birthDay,
        String birthPlace,
        Integer id,
        String name,
        String patronymic,
        String surname
) {
}
