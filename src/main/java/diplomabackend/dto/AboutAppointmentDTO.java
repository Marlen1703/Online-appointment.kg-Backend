package diplomabackend.dto;

import diplomabackend.domain.Appointment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AboutAppointmentDTO {

    private String email;
    private String phone;

    private LocalDateTime dateOfBirth;

    private String diagnosis;

    private int pressure;

    private int temperature;

    private int weight;

    private int height;


    public AboutAppointmentDTO(Appointment appointment) {
        this.email = appointment.getConsumer().getEmail();
        this.phone = appointment.getConsumer().getPhone();
        this.dateOfBirth = appointment.getConsumer().getDateOfBirth();
        this.diagnosis = appointment.getDiagnosis();
        this.pressure = appointment.getConsumer().getPressure();
        this.temperature = appointment.getConsumer().getTemperature();
        this.weight = appointment.getConsumer().getWeight();
        this.height = appointment.getConsumer().getHeight();
    }
}
