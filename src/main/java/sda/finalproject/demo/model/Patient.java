package sda.finalproject.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private Integer phoneNumber;
    private Integer CNP;

    @ManyToOne
    private User user;

    @Lob
    private Byte[] image;
}
