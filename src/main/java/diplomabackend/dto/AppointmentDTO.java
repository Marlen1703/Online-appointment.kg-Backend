package diplomabackend.dto;

import diplomabackend.StatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentDTO {


    private LocalDateTime time;

    private DoctorDTO attendingDoctor;

    private String diagnosis;

    private String policy;

    private StatusEnum status;
}