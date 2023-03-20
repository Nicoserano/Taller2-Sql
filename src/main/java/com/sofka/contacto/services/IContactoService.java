package com.sofka.contacto.services;

import com.sofka.contacto.domain.Contacto;

import java.util.List;
import java.util.Optional;

public interface IContactoService {

    public List<Contacto> getlist();

    public List<Contacto> searchContacto(String dataToSearch);
    public Contacto createContacto(Contacto contacto);
    Contacto deleteContacto(Integer id);

    public Contacto updateContacto(Integer id, Contacto contacto);

    public Contacto searchById(Integer id);



}
