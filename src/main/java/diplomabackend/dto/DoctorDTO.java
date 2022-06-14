package diplomabackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DoctorDTO {


    private String firstName;

    private String lastName;

    private String middleName;

    private String email;

    private String phone;

    private int experience;

    private String speciality;

    private String avatar;

    private int rating;

    private String education;



}
