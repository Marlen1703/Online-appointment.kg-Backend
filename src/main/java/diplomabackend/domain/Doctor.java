package diplomabackend.domain;

import diplomabackend.GenderEnum;
import lombok.Data;

import javax.persistence.*;

@Table
@Entity
@Data
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


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

    private String password;


}
