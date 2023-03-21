package com.sofka.contacto.dao;

import com.sofka.contacto.domain.Contacto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactoDao extends CrudRepository<Contacto, Integer> {

    // Consulta que busca contactos cuyo nombre comienza con la cadena de caracteres especificada
    @Query(value = "SELECT cnt " +
            "FROM Contacto cnt " +
            "WHERE (cnt.nombre LIKE :data% ) " +
            "ORDER BY cnt.nombre ASC")
    public List<Contacto> findByNombreStartingWith(@Param("data") String data);

    // Consulta que busca contactos cuyo nombre contiene la cadena de caracteres especificada
    @Query(value = "SELECT cnt " +
            "FROM Contacto cnt " +
            "WHERE cnt.nombre LIKE %:data% " +
            "ORDER BY cnt.nombre ASC")
    public List<Contacto> findByNombreContains(@Param("data") String data);

    // Consulta que busca contactos cuyo nombre termina con la cadena de caracteres especificada
    @Query(value = "SELECT cnt " +
            "FROM Contacto cnt " +
            "WHERE cnt.nombre LIKE %:data " +
            "ORDER BY cnt.nombre ASC")
    public List<Contacto> findByNombreEndingWith(@Param("data") String data);

}
