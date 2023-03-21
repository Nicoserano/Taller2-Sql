package com.sofka.contacto.controller;

import com.sofka.contacto.domain.Contacto;
import com.sofka.contacto.services.ContactoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
/*
* Controlador de Spring para la gestión de contactos
*
* @Author Ronald serrano
* */
public class ContactoController {

    // Inyecta la dependencia del servicio de contactos en el controlador mediante la anotación @Autowired
    @Autowired
    private ContactoService contactoService;


    // Ruta para el home
    @GetMapping("/home")
    String indexString() {
        return "home";
    }

    // Ruta para la lista de contactos
    @GetMapping(path = "/contactos")
    public String index(Model model) {
        List<Contacto> contacto = contactoService.getlist();
        model.addAttribute("contacto", contacto);
        return "Index";
    }

    // Ruta para mostrar el formulario de creación de contacto
    @GetMapping(path = "/contactos/nuevo")
    public String formulario() {
        return "formulario";
    }

    // Ruta para mostrar la página de búsqueda de contacto
    @GetMapping(path = "/contactos/buscar")
    public String busqueda() {
        return "busquedad";
    }

    // Ruta para mostrar el resultado de la búsqueda de contacto
    @GetMapping(path = "/contactos/buscar/resultado")
    public String Buscar( Model model, @RequestParam(required = false) String nombre){
        List<Contacto> contacto = contactoService.searchContacto(nombre);
        model.addAttribute("contacto", contacto);
        return "resultadoBusquedad";
    }

    // Ruta para crear un nuevo contacto a partir de los datos del formulario
    @PostMapping("/contactos/nuevo/creado")
    public String crearContacto(@RequestParam("nombre") String nombre,
                                @RequestParam("celular") String celular,
                                @RequestParam("email") String email,
                                @RequestParam("fecha_nacimiento") String fecha_nacimiento) {

        // Crear un objeto de tipo Contacto con los datos del formulario
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

        // Guardar el nuevo contacto en la base de datos
        contactoService.createContacto(contacto);

        // Redireccionar al formulario para crear otro contacto
        return "/formulario";
    }

    // Ruta para mostrar la página de edición de un contacto
    @GetMapping("/contactos/{id}/editar")
    public String verContacto(@PathVariable("id") Integer id, Model model) {
        Contacto contacto = contactoService.searchById(id);
        model.addAttribute("contacto", contacto);
        return "contacto";
    }

    // Ruta para mostrar el formulario de edición de un contacto
    @GetMapping("/contactos/{id}/editar/datos")
    public String Editar(@PathVariable Integer id, Model modelo){
        modelo.addAttribute("contacto", contactoService.searchById(id));
        return "editar";
    }
    //Ruta para la vista previa a elimnar contacto
    @GetMapping("/contactos/{id}/eliminar")
    public String borrarContacto(@PathVariable Integer id, Model model) {
        // Obtiene el contacto con el ID especificado
        Contacto contacto = contactoService.searchById(id);
        // Agrega el contacto al modelo para que pueda ser utilizado en la vista
        model.addAttribute("contacto", contacto);
        // Devuelve la vista "eliminar" para que el usuario confirme que desea eliminar el contacto
        return "eliminar";
    }

    //Ruta para eliminar contacto
    @GetMapping("/contactos/{id}/elimina")
    public String eliminarContacto(@PathVariable("id") Integer id) {
        // Elimina el contacto con el ID especificado
        contactoService.deleteContacto(id);
        // Redirige al usuario a la lista de contactos
        return "redirect:/contactos";
    }

    //Ruta para guardar los datos del contacto editado
    @PostMapping(path = "/contactos/{id}/edit")
    public String Nombre(@PathVariable Integer id, @ModelAttribute("contacto") Contacto contacto, Model model){
        // Obtiene el contacto existente con el ID especificado
        Contacto contactoExiste = contactoService.searchById(id);
        // Actualiza los datos del contacto con los valores recibidos del formulario
        contactoExiste.setId(id);
        contactoExiste.setNombre(contacto.getNombre());
        contactoExiste.setCelular(contacto.getCelular());
        contactoExiste.setEmail(contacto.getEmail());
        contactoExiste.setFecha_nacimiento(contacto.getFecha_nacimiento());
        // Actualiza el contacto en la base de datos
        contactoService.updateContacto(id,contactoExiste);
        // Redirige al usuario a la página de edición del contacto
        return "redirect:/contactos/{id}/editar";
    }
















}
