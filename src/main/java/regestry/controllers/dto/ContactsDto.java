package regestry.controllers.dto;

public record ContactsDto(
        Long phone_number,
        String email,
        String real_address,
        Integer citizens_id)
  {
}
