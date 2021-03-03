package sda.finalproject.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class ToothFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer toothName;

    @NotNull
    private String diagnostic;

    @Lob
    @NotNull
    private String treatment;

    @ManyToOne
    @JsonIgnore
    private DentalFile dentalFile;
}
