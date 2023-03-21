package com.sofka.contacto.services;

import com.sofka.contacto.domain.Contacto;
import com.sofka.contacto.dao.ContactoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class ContactoService implements IContactoService{

    @Autowired
    private ContactoDao contactoDao;

    // Método para obtener una lista con todos los contactos
    @Override
    @Transactional(readOnly = true)
    public List<Contacto> getlist() {
        return (List<Contacto>)contactoDao.findAll();
    }

    // Método para buscar contactos que contengan una cadena de texto dada en el nombre
    @Override
    public List<Contacto> searchContacto(String dataToSearch) {
        // Busca los contactos que comiencen con la cadena dada
        var contacto1 = contactoDao.findByNombreStartingWith(dataToSearch);
        // Busca los contactos que contengan la cadena dada en cualquier lugar del nombre
        var contacto2 = contactoDao.findByNombreContains(dataToSearch);
        // Busca los contactos que terminen con la cadena dada
        var contacto3 = contactoDao.findByNombreEndingWith(dataToSearch);
        // Combina los resultados de las búsquedas anteriores en un conjunto para eliminar duplicados
        var answer = new HashSet<Contacto>();
        answer.addAll(contacto1);
        answer.addAll(contacto2);
        answer.addAll(contacto3);
        // Convierte el conjunto en una lista y la devuelve
        return answer.stream().toList();
    }

    // Método para crear un nuevo contacto
    @Override
    public Contacto createContacto(Contacto contacto) {
        return contactoDao.save(contacto);
    }

    // Método para actualizar un contacto existente
    @Override
    @Transactional
    public Contacto updateContacto(Integer id, Contacto contacto) {
        // TODO: Implementar la lógica para actualizar un contacto
        // Aquí se debería obtener el contacto existente con el ID dado, actualizar sus campos
        // con los valores del objeto contacto que se pasó como parámetro, y luego guardar los cambios
        // en la base de datos a través del ContactoDao
        return contacto;
    }

    // Método para eliminar un contacto existente
    @Override
    @Transactional
    public Contacto deleteContacto(Integer id) {
        // Busca el contacto con el ID dado
        var contacto = contactoDao.findById(id);
        if (contacto.isPresent()) {
            // Si se encontró el contacto, se elimina y se devuelve el objeto Contacto eliminado
            contactoDao.delete(contacto.get());
            return contacto.get();
        } else {
            // Si no se encontró el contacto, devuelve null
            return null;
        }
    }

    // Método para buscar un contacto por su ID
    @Override
    public Contacto searchById(Integer id) {
        Optional<Contacto> contactoOptional = contactoDao.findById(id);
        // Devuelve el objeto Contacto si se encontró, o null si no se encontró
        return contactoOptional.orElse(null);
    }

}
