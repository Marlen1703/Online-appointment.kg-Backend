package diplomabackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import diplomabackend.StatusEnum;
import diplomabackend.domain.Appointment;
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
