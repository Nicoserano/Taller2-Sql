package com.sofka.contacto.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data // Utiliza Lombok para generar getters, setters y otros métodos básicos automáticamente.
@Entity // Anotación para indicar que esta clase es una entidad que se mapeará en una tabla de la base de datos.
@Table(name="contacto") // Indica que esta entidad se mapeará en una tabla llamada "contacto".
public class Contacto implements Serializable {

    private static  final long serialVersion = 1l; // Versión de serialización.

    @Id // Indica que este campo es la clave primaria de la entidad.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que el valor de esta clave se generará automáticamente.
    @Column(name = "id_contacto") // Indica que este campo se mapeará a la columna "id_contacto" en la tabla.
    private Integer id ;

    @Column(name = "nombre") // Indica que este campo se mapeará a la columna "nombre" en la tabla.
    private String nombre ;

    @Column(name = "celular") // Indica que este campo se mapeará a la columna "celular" en la tabla.
    private String celular;

    @Column(name = "email") // Indica que este campo se mapeará a la columna "email" en la tabla.
    private String email;

    @Column(name = "fecha_nacimiento") // Indica que este campo se mapeará a la columna "fecha_nacimiento" en la tabla.
    private Date fecha_nacimiento;

}
