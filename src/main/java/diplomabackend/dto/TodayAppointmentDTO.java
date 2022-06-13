package diplomabackend.dto;

import diplomabackend.domain.Appointment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodayAppointmentDTO {

    private String name;

    private LocalDateTime time;

    public TodayAppointmentDTO(Appointment appointment) {
        this.name=appointment.getConsumer().getFirstName()+" "+appointment.getConsumer().getLastName();
        this.time = appointment.getTime();
    }
}
