package com.sofka.contacto.dao;

import com.sofka.contacto.domain.Contacto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactoDao extends CrudRepository<Contacto, Integer> {

    @Query(value = "SELECT cnt " +
            "FROM Contacto cnt " +
            "WHERE (cnt.nombre LIKE :data% ) " +
            "ORDER BY cnt.nombre ASC")
    public List<Contacto> findByNombreStartingWith(@Param("data") String data);


    @Query(value = "SELECT cnt " +
            "FROM Contacto cnt " +
            "WHERE cnt.nombre LIKE %:data% " +
            "ORDER BY cnt.nombre ASC")
    public List<Contacto> findByNombreContains(@Param("data") String data);


    @Query(value = "SELECT cnt " +
            "FROM Contacto cnt " +
            "WHERE cnt.nombre LIKE %:data " +
            "ORDER BY cnt.nombre ASC")
    public List<Contacto> findByNombreEndingWith(@Param("data") String data);


    @Modifying
    @Query(value = "update Contacto cnt set cnt.nombre = :nombre where cnt.id = :id")
    public void updateNombre(@Param(value = "id") Integer id, @Param(value = "nombre") String nombre);

    @Modifying
    @Query(value = "update Contacto cnt set cnt.celular= :celular where cnt.id = :id")
    public void updateCelular(@Param(value = "id") Integer id, @Param(value = "celular") String celular);
    @Modifying
    @Query(value = "update Contacto cnt set cnt.email= :email where cnt.id = :id")
    public void updateEmail(@Param(value = "id") Integer id, @Param(value = "email") String email);





}
