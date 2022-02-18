package diplomabackend.dto;

import com.sun.istack.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class RegistrationResponseDTO {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String middleName;

    @NotNull
    private Date dateOfBirth;

    @NotNull
    private String username;

    private String token;

}
