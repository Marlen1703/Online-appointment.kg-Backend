package diplomabackend.domain;

import diplomabackend.StatusEnum;
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
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDateTime time;

    @ManyToOne
    private Doctor attendingDoctor;

    private String diagnosis;

    private String policy;

    private StatusEnum status;

    @ManyToOne
    private Consumer consumer;

}
