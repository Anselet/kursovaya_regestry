package regestry.controllers.dto;

import java.time.LocalDate;

public record CertificatesDto(String date_of_issue,
                              Integer subnumber,
                              Integer number,
                              Integer reg_number,
                              Integer cert_type_id) {
}
