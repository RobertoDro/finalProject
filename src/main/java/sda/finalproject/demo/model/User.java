package sda.finalproject.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String specialty;
    private Integer phoneNumber;
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    private Set<Patient> patients = new HashSet<>();


}
