package regestry.controllers.dto;

public record ContactsUpdateDto(
        Integer citizen,
        String email,
        Integer id,
        Long phone_number,
        String real_address
) {
}
