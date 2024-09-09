package org.userinputs.entity;

import jakarta.persistence.*;

import lombok.Data;


import java.io.Serializable;


@Entity
@Table(name = "mandatoryfields")
@Data
public class MandatoryFieldsEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "tablename")
    private String tablename;

    @Column(name = "reqfieldname")
    private String reqfieldname;

}
