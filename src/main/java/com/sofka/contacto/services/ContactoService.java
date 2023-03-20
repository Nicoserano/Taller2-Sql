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


    @Override
    @Transactional(readOnly = true)
    public List<Contacto> getlist() {
        return (List<Contacto>)contactoDao.findAll();
    }



    @Override

    public List<Contacto> searchContacto(String dataToSearch) {
        var contacto1 = contactoDao.findByNombreStartingWith(dataToSearch);
        var contacto2 = contactoDao.findByNombreContains(dataToSearch);
        var contacto3 = contactoDao.findByNombreEndingWith(dataToSearch);
        var answer = new HashSet<Contacto>();
        answer.addAll(contacto1);
        answer.addAll(contacto2);
        answer.addAll(contacto3);
        return answer.stream().toList();
    }

    @Override
    public Contacto createContacto(Contacto contacto) {
        return contactoDao.save(contacto);
    }



    @Override
    @Transactional
    public Contacto updateContacto(Integer id, Contacto contacto) {
        contactoDao.findById(id);
        contacto.setId(id);
        contactoDao.updateNombre(id, contacto.getNombre());
        return contacto;
    }

    @Override
    @Transactional
    public Contacto deleteContacto(Integer id) {
        var contacto = contactoDao.findById(id);
        if (contacto.isPresent()) {
            contactoDao.delete(contacto.get());
            return contacto.get();
        } else {
            return null;
        }
    }




    @Override
    public Contacto searchById(Integer id) {
        Optional<Contacto> contactoOptional = contactoDao.findById(id);
        return contactoOptional.orElse(null);
    }


}
