# Taller2-Sql

<h2>Taller sofka sql creando un crud</h2>

Este proyecto consiste en un sistema CRUD que permite el control de contactos telefónicos. Se utiliza una base de datos
relacional como MySQL y se trabaja con Spring Boot, JPA y API Rest.

La aplicación consta de un frontend creado con html y un backend que proporciona las API
Rest para que el frontend pueda interactuar con la base de datos.

La información que se almacena para cada contacto incluye su nombre completo, teléfono, correo electrónico y fecha de
nacimiento.

La aplicación permite el borrado lógico y físico de contactos.

<h2>Tecnologías utilizadas</h2>
<ul>
<li>Java</li>
<li>Spring Boot</li>
<li>JPA</li>
<li>MySQL</li>
<li>HTML</li>
</ul>
<h2>Configuración</h2>
Antes de ejecutar la aplicación, asegúrese de tener una base de datos MySQL creada y configurada en el archivo
application.properties.

En el archivo pom.xml, se deben incluir las dependencias de Spring Boot, JPA y MySQL Connector.

Para ejecutar la aplicación, ejecute la clase Application.java como una aplicación Java.

<h2>Ruta principal</h2>
localhost:8080/home