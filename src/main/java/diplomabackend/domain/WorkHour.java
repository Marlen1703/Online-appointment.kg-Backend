package diplomabackend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkHour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDateTime bookTime;

    private boolean isAvailable;

    private Long doctorId;

}
