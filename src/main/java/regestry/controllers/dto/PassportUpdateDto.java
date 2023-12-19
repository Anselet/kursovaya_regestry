package regestry.controllers.dto;

public record PassportUpdateDto(
        Integer citizen,
        String date,
        String division_code,
        String division_name,
        Integer id,
        Integer number,
        Integer subnumber
) {
}
