package diplomabackend.dto;

import diplomabackend.domain.WorkHour;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WorkHourDTO {

    private LocalDateTime bookTime;

    private boolean isAvailable;

    public WorkHourDTO(WorkHour workHour) {
        this.bookTime = workHour.getBookTime();
        this.isAvailable = workHour.isAvailable();
    }
}
