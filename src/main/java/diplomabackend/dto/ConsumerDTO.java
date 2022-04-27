package diplomabackend.dto;

import diplomabackend.domain.MedicalCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerDTO {

    private String firstName;

    private String lastName;

    private String middleName;

    private String username;


    private String address;


    private String city;


    private String email;

    private MedicalCardDTO medicalCard;

}
