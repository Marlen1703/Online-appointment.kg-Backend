package diplomabackend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NewAppointmentDTO {

    private String policy;

    private long doctorId;

    private LocalDateTime time;

}
