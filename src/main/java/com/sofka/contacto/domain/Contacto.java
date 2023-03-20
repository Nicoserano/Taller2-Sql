package com.sofka.contacto.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="contacto")
public class Contacto implements Serializable {

    private static  final long serialVersion = 1l;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contacto")
    private Integer id ;
    @Column(name = "nombre")
    private String nombre ;
    @Column(name = "celular")
    private String celular;
    @Column(name = "email")
    private String email;
    @Column(name = "fecha_nacimiento")
    private Date fecha_nacimiento;




}
