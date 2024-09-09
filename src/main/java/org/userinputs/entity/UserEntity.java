package org.userinputs.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "usersdetails")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "birthdate")
    private String birthdate;
    @Column(name = "birthplace")
    private String birthplace;
    @Column(name = "sex")
    private String sex;
    @Column(name = "currentaddress")
    private String currentaddress;
}
