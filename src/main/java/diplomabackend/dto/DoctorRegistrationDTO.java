package diplomabackend.dto;

import lombok.Data;

@Data
public class DoctorRegistrationDTO {

    private String firstName;

    private String lastName;

    private String middleName;


    private String email;

    private String phone;

    private int experience;

    private String speciality;


    private int rating;

    private String education;

    private String password;
}
