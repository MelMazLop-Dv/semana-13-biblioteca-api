# BIBLIOTECA PERSONAL

> Una aplicación backend desarrollada con Spring Boot y PostgreSQL que permite gestionar una biblioteca personal mediante solicitudes HTTP (POST, GET, PUT, DELETE), actuando como una API REST completa.

---

## Características Principales

- **Gestión de Libros:** Registrar, listar, actualizar y eliminar libros de la biblioteca.
- **Conexión a Base de Datos:** Persistencia de datos robusta utilizando PostgreSQL y Spring Data JPA.
- **Arquitectura RESTful:** Endpoints limpios y estructurados para interactuar mediante herramientas como Postman, cURL o clientes frontend.
- **Manejo de Errores:** Respuestas HTTP estandarizadas (`200 OK`, `201 Created`, `404 Not Found`, etc.).

---

## Tecnologías Utilizadas

- **Java 17+**
- **Spring Boot** (Spring Web, Spring Data JPA, Spring Boot DevTools)
- **PostgreSQL** (Base de datos relacional)
- **Maven** (Gestor de dependencias)
- **Postman** (Pruebas de endpoints HTTP)

---

## Estructura del proyecto

El proyecto sigue una arquitectura por capas estándar para aplicaciones en Spring Boot, separando las responsabilidades de control, lógica, acceso a datos y persistencia:

```text
biblioteca-personal/
├── .mvn/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── biblioteca/
│   │   │           ├── BibliotecaApplication.java       # Clase principal para arrancar Spring Boot
│   │   │           ├── controller/                      # Capa de presentación: Endpoints HTTP (REST APIs)
│   │   │           ├── model/                           # Capa de datos: Entidades JPA mapeadas a PostgreSQL
│   │   │           ├── repository/                      # Capa de persistencia: Interfaces de Spring Data JPA
│   │   │           └── service/                         # Capa de lógica de negocio y servicios
│   │   │               └── impl/                        # Implementaciones de las interfaces de servicio
│   │   │
│   │   └── resources/
│   │       └── application.properties                   # Configuración de la conexión a PostgreSQL y puerto
│   │
│   └── test/                                            # Pruebas unitarias e de integración
│
├── .gitignore
├── pom.xml                                              # Dependencias de Maven (Spring Web, JPA, PostgreSQL)
└── README.md                                            # Documentación principal del proyecto
## Configuración base de datos

1. **Crea la base de datos en PostgreSQL:**
   Abre tu cliente de PostgreSQL (como pgAdmin o la terminal psql) y ejecuta:
   ```sql
   CREATE DATABASE biblioteca_db;
   
## Tabla de endpoints

La API expone los siguientes endpoints para interactuar desde Postman u otro cliente HTTP. La URL base por defecto es `http://localhost:8080`.

| Método HTTP | Endpoint | Descripción | Ejemplo de Body (JSON) | Código de Respuesta |
| :--- | :--- | :--- | :--- | :--- |
| **GET** | `/api/libros` | Obtiene la lista completa de libros | *Ninguno* | `200 OK` |
| **GET** | `/api/libros/{id}` | Busca un libro por su ID | *Ninguno* | `200 OK` / `404 Not Found` |
| **POST** | `/api/libros` | Registra un nuevo libro en la BD | Ver ejemplo abajo | `201 Created` |
| **PUT** | `/api/libros/{id}` | Actualiza un libro existente | Ver ejemplo abajo | `200 OK` / `404 Not Found` |
| **DELETE** | `/api/libros/{id}` | Elimina un libro por su ID | *Ninguno* | `204 No Content` |

### Ejemplo de JSON para solicitudes `POST` y `PUT`

Al realizar peticiones de tipo **POST** o **PUT** en Postman, asegúrate de enviar los datos en formato JSON seleccionando `Body` > `raw` > `JSON`:

```json
{
  "titulo": "Cien años de soledad",
  "autor": "Gabriel García Márquez",
  "isbn": "978-0307474728",
  "anioPublicacion": 1967,
  "leido": false
}
```

## Instrucciones para la ejecución

Sigue estos pasos para poner en marcha la API en tu entorno local:

### 1. Requisitos Previos
Asegúrate de tener instalado en tu computadora:
- **Java JDK** (versión 17 o superior).
- **Maven** (o puedes usar el Maven Wrapper `mvnw` incluido).
- **PostgreSQL** ejecutándose localmente.
- **Postman** (o cualquier cliente HTTP para probar los endpoints).

---

### 2. Configurar la Base de Datos
1. Abre tu cliente de PostgreSQL (como pgAdmin o la terminal) y crea una base de datos nueva:
   ```sql
   CREATE DATABASE biblioteca_db;
   
## Nombre 
- Melvin Apolonio Mazariegos López
## Carné
- 9941 25 25174
