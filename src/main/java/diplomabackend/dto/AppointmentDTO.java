package diplomabackend.dto;

import diplomabackend.enums.StatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentDTO {


    private Long id;

    private LocalDateTime time;

    private DoctorDTO attendingDoctor;

    private String diagnosis;

    private String policy;

    private StatusEnum status;

    private ConsumerDTO consumer;

}
