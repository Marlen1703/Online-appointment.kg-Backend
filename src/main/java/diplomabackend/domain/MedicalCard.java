package diplomabackend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String personalIdentifier;


    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }


    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "owner_id")
    private Consumer owner;
}
