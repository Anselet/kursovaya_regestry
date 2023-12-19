package regestry.controllers.dto;

import java.time.LocalDate;

public record CitizenDto(
        String name,
        String surname,
        String patronymic,
        String birthDay,
        String birthPlace) {

}
