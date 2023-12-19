package regestry.controllers.dto;

public record RegistrationUpdateDto(
        String cancellation_date,
        String date,
        String districts,
        String division_code,
        String division_name,
        Integer flat_number,
        String house_number,
        Integer id,
        Integer passport,
        String population_center,
        String regions,
        String state,
        String streets,
        String subjects
) {
}
