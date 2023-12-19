package regestry.controllers.dto;

public record CertificatesUpdateDto(
        Integer acts,
        Integer certificateType,
        String date_of_issue,
        Integer id,
        Integer number,
        Integer subnumber
) {
}
