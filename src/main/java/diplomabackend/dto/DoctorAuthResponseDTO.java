package diplomabackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorAuthResponseDTO {
    private DoctorDTO doctor;
    private String access_token;
}
