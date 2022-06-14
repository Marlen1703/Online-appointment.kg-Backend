package diplomabackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.sun.istack.NotNull;
import diplomabackend.GenderEnum;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
public class RegistrationRequestDTO {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String middleName;


//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private LocalDateTime dateOfBirth;

    @NotNull
    private String username;


    @NotNull
    private String password;

    @NotNull
    private String address;


@NotNull
    private MultipartFile avatar;


    @NotNull
    private GenderEnum gender;

    @NotNull
    private String city;

    @NotNull
    private String email;

}
