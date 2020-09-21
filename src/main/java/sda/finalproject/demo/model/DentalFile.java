package sda.finalproject.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class DentalFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String diagnostic;
    private Date date;

    @Lob
    private String treatment;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tooth")
    private Set<Tooth> toothList = new HashSet<>();
}
