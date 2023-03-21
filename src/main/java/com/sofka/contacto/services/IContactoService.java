package com.sofka.contacto.services;

import com.sofka.contacto.domain.Contacto;

import java.util.List;


public interface IContactoService {

    // Método para obtener la lista completa de contactos
    public List<Contacto> getlist();

    // Método para buscar contactos por un término de búsqueda
    public List<Contacto> searchContacto(String dataToSearch);

    // Método para crear un nuevo contacto
    public Contacto createContacto(Contacto contacto);

    // Método para eliminar un contacto dado su identificador
    public Contacto deleteContacto(Integer id);

    // Método para actualizar un contacto existente dado su identificador
    public Contacto updateContacto(Integer id, Contacto contacto);

    // Método para buscar un contacto dado su identificador
    public Contacto searchById(Integer id);


}
