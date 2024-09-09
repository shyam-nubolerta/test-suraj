package org.userinputs.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "externalservice")
@IdClass(value =ExternalServiceEntityID.class)
public class ExternalServiceEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Id
    @Column(name = "servicename")
    private String servicename;
    @Column(name = "url")
    private String url;

}
