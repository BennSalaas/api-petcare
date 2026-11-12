# API Clínica Veterinaria "PetCare"

Backend desarrollado con Spring Boot aplicando arquitectura en capas (Entity -> Repository -> Service -> Controller).

## Logrado
- Configuración de proyecto Spring Boot con Spring Web, Spring Data JPA, H2 y Lombok.
- Modelado de entidades (Dueno, Mascota, Cita) con relaciones @OneToMany y @ManyToOne.
- Validaciones de negocio en la capa Service (RUT único y verificación de existencia de dueños/mascotas).
- Controladores REST con CRUD completo (GET, POST, PUT, DELETE) y endpoints relacionales (/api/duenos/{id}/mascotas y /api/mascotas/{id}/citas).
- Pruebas funcionales en Postman (disponibles en la carpeta /evidencias).

## Pendiente
- Implementación del cliente frontend en React para consumir los endpoints.
