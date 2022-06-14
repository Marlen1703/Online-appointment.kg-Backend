package diplomabackend.dto;

import diplomabackend.domain.Appointment;
import lombok.Data;

@Data
public class DiagnoseDTO {

    private Long id;

    private String diagnosis;

    private int pressure;

    private int height;


    private int weight;

    private int temperature;

    
}
