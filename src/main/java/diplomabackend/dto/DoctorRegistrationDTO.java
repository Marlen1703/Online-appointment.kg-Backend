package diplomabackend.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class DoctorRegistrationDTO {

    private String firstName;

    private String lastName;

    private String middleName;


    private String email;

    private String phone;

    private int experience;

    private String speciality;

    private MultipartFile avatar;

    private String education;

    private String password;
}
