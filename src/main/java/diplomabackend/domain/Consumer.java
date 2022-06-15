package diplomabackend.domain;


import diplomabackend.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


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

    private LocalDateTime dateOfBirth;

    private String username;

    private String password;

    @OneToOne
    @JoinColumn(name = "medical_card_id")
    private MedicalCard medicalCard;

    private String city;

    private String email;

    private GenderEnum gender;

    private int weight;

    private int height;

    private int temperature;

    private int pressure;

    private String phone;

    private String avatar;

    private String address;
}
