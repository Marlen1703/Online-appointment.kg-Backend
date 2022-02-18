package diplomabackend.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String firstName;

    private String lastName;

    private String middleName;

    private Date  dateOfBirth;

    private String username;

    private String password;

    @OneToOne
    @JoinColumn(name = "medical_card_id")
    private MedicalCard medicalCard;

}
