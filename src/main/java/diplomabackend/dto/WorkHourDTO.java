package diplomabackend.dto;

import diplomabackend.domain.WorkHour;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WorkHourDTO {

    private String workHour;

    private boolean available;


    public WorkHourDTO(WorkHour workHour) {
        this.workHour = workHour.getWorkHour();
        this.available=true;
    }
}
