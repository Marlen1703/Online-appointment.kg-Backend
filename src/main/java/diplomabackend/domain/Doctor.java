package diplomabackend.domain;

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

    private int age;

    private int experience;

    private String speciality;

    private String avatar;

    private int rating;

    private String education;


}
