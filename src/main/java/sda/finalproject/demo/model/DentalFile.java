package sda.finalproject.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Data
@Entity
public class DentalFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    private String dentalImplants;

    private String missingTeeth;


    @OneToMany(cascade = CascadeType.ALL,mappedBy ="dentalFile")
    private List<ToothFile> toothFiles = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    private Patient patient;


}
