package sda.finalproject.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Min(10)
    private String phoneNumber;
    @Min(13)
    private String cnp;

    private String street;
    @NotNull
    private Integer numberOfStreet;
    @NotNull
    private Integer apartment;

    @Lob
    private byte[] image;

    @Lob
    private byte[] document;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    private List<DentalFile> dentalFiles = new ArrayList<>();


}
