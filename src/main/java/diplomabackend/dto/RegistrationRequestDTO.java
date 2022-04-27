package diplomabackend.dto;

import com.sun.istack.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegistrationRequestDTO {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String middleName;

    @NotNull
    private LocalDateTime dateOfBirth;

    @NotNull
    private String username;


    @NotNull
    private String password;


    @NotNull
    private String address;


    @NotNull
    private String city;

    @NotNull
    private String email;


}
