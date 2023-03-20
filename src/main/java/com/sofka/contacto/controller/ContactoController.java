package com.sofka.contacto.controller;

import com.sofka.contacto.domain.Contacto;
import com.sofka.contacto.services.ContactoService;
import com.sofka.contacto.utility.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@Slf4j
public class ContactoController {
    @Autowired
    private ContactoService contactoService;

    private Response response = new Response();
    private HttpStatus httpStatus = HttpStatus.OK;

    //home
    @GetMapping("/home")
     String indexString() {
        return "home";
    }
    //lista de contactos
    @GetMapping(path = "/contactos")
    public String index(Model model) {
        List<Contacto> contacto = contactoService.getlist();
        model.addAttribute("contacto", contacto);
        return "Index";
    }
    //crear contacto
    @GetMapping(path = "/contactos/nuevo")
    public String formulario() {
        return "formulario";
    }
    //Buscar contacto
    @GetMapping(path = "/contactos/buscar")
    public String busqueda() {
        return "busquedad";
    }


    @GetMapping(path = "/contactos/buscar/resultado")
    public String Buscar( Model model, @RequestParam(required = false) String nombre){
        List<Contacto> contacto = contactoService.searchContacto(nombre);
        model.addAttribute("contacto", contacto);
        return "resultadoBusquedad";
    }



    @PostMapping("/contactos/nuevo/creado")
    public String crearContacto(@RequestParam("nombre") String nombre,
                                @RequestParam("celular") String celular,
                                @RequestParam("email") String email,
                                @RequestParam("fecha_nacimiento") String fecha_nacimiento) {

        Contacto contacto = new Contacto();
        contacto.setNombre(nombre);
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaNacimiento = formatoFecha.parse(fecha_nacimiento);
            contacto.setFecha_nacimiento(fechaNacimiento);
        } catch (ParseException e) {
            // Manejar excepción en caso de que el formato de la fecha no sea válido
        }
        contacto.setCelular(celular);
        contacto.setEmail(email);

        contactoService.createContacto(contacto);

        return "/formulario";
    }

    @GetMapping("/contactos/{id}/editar")
    public String verContacto(@PathVariable("id") Integer id, Model model) {
        Contacto contacto = contactoService.searchById(id);
        model.addAttribute("contacto", contacto);
        return "contacto";
    }
    @GetMapping("/contactos/{id}/editar/datos")
    public String Editar(@PathVariable Integer id, Model modelo){
        modelo.addAttribute("contacto", contactoService.searchById(id));
        return "editar";
    }
    @GetMapping("/contactos/{id}/eliminar")
    public String borrarContacto(@PathVariable Integer id, Model model) {
        Contacto contacto = contactoService.searchById(id);
        model.addAttribute("contacto", contacto);
        return "eliminar";
    }

    @GetMapping  ("/contactos/{id}/elimina")
    public String eliminarContacto(@PathVariable("id") Integer id) {
        contactoService.deleteContacto(id);
        return "redirect:/contactos";
    }
    @PostMapping(path = "/contactos/{id}/edit")
    public String Nombre(@PathVariable Integer id, @ModelAttribute("contacto") Contacto contacto, Model model){
        Contacto contactoExiste = contactoService.searchById(id);
        contactoExiste.setId(id);
        contactoExiste.setNombre(contacto.getNombre());
        contactoExiste.setCelular(contacto.getCelular());
        contactoExiste.setEmail(contacto.getEmail());
        contactoExiste.setFecha_nacimiento(contacto.getFecha_nacimiento());
        contactoService.updateContacto(id,contactoExiste);
        return "redirect:/contactos/{id}/editar";
    }















}
