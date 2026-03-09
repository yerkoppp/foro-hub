# 🚀 ForoHub - API REST (Challenge Alura Latam)
___
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=spring-security&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=json-web-tokens&logoColor=white)
![Flyway](https://img.shields.io/badge/Flyway-CC0202?style=for-the-badge&logo=flyway&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=white)
![Maven](https://img.shields.io/badge/Apache_Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)



ForoHub es la réplica a nivel de back-end de un foro técnico, desarrollada como parte del programa Oracle Next Education (ONE).
Esta API REST permite a los usuarios gestionar el ciclo de vida completo de los tópicos de discusión, interactuar mediante respuestas y mantener un control de acceso seguro.
___
# 🛠️ Stack Tecnológico (V. 2026)
 * Java 17: Lenguaje base para el desarrollo del proyecto.
 * Spring Boot 4.0.3: Framework utilizado para facilitar el desarrollo de la API REST.
 * Spring Data JPA: Implementación para la persistencia de datos en la base de datos.
 * Spring Security: Servicio de autenticación y autorización para restringir el acceso.
 * Auth0 Java JWT (4.5.1): Estándar utilizado para compartir información segura mediante tokens.
 * Flyway Migration: Herramienta para la gestión y migración de la base de datos SQL.
 * MySQL 8.0: Base de datos relacional utilizada para almacenar la información.
 * Lombok: Reducción de código repetitivo mediante anotaciones.
 * SpringDoc OpenAPI (2.8.5): Documentación automática e interfaz gráfica de la API.
___
# ⚙️ Configuración e Instalación

### 1. Base de Datos y Persistencia
   
El sistema utiliza Flyway para gestionar el ciclo de vida de la base de datos. Al iniciar la aplicación, se ejecutan automáticamente tres migraciones:
    
- V1: Crea las tablas usuarios, cursos y topicos.
- V2: Inserta el catálogo inicial de cursos (Android, Kotlin, Spring, etc.).
- V3: Crea la tabla respuestas con sus relaciones de integridad.


### 2. Configuración de Aplicación (application.properties)
   Asegúrate de configurar los parámetros de conexión en src/main/resources/application.properties:

`application.properties`
````
spring.datasource.url=jdbc:mysql://${DB_HOST}/forohub
spring.datasource.username=root
spring.datasource.password=${DB_PASSWORD}

# Secreto para la firma de tokens JWT
api.security.token.secret=${JWT_SECRET:12345678}
````
### 3. Pruebas en Insomnia
-[x] Registro: Crea un usuario en POST /registrar.
-[x] Login: Autentícate en POST /login para obtener tu Token JWT.
-[x] Autorización: En cada petición posterior, añade el token en la pestaña Auth > Bearer Token de Insomnia.
---
# 📋 Documentación de la API (Endpoints)

| Recurso | Método | Endpoint | Descripción                                                        | Acceso |
| :--- | :--- | :--- |:-------------------------------------------------------------------| :--- |
| **Autenticación** | `POST` | `/login` | Autentica un usuario y devuelve el Token JWT.                      | 🔓 Público |
| **Registro** | `POST` | `/registrar` | Crea una nueva cuenta de usuario.                                  | 🔓 Público |
| **Tópicos** | `POST` | `/topicos` | Registra un nuevo tópico (Título, Mensaje, Autor, Curso).          | 🔐 Privado |
| **Tópicos** | `GET` | `/topicos` | Lista todos los tópicos con paginación y ordenamiento.             | 🔐 Privado |
| **Tópicos** | `GET` | `/topicos/{id}` | Detalle de un tópico específico y sus respuestas.                  | 🔐 Privado |
| **Tópicos** | `PUT` | `/topicos/{id}` | Actualiza el título o mensaje de un tópico existente.              | 🔐 Privado |
| **Tópicos** | `DELETE` | `/topicos/{id}` | Elimina un tópico de la base de datos de forma permanente. | 🔐 Privado |
| **Respuestas** | `POST` | `/respuestas` | Registra una respuesta vinculada a un tópico.                      | 🔐 Privado |
| **Solución** | `PUT` | `/respuestas/{id}/solucion` | Marca una respuesta como la solución oficial del tópico.           | 🔐 Privado |

## 📝 Ejemplos de JSON (Request Bodies)
Esta estructura asegura que se cumplan las reglas de negocio, como el envío de todos los campos obligatorios y el formato correcto para la persistencia en la base de datos.

### 1. Autenticación (`POST /login`)
`JSON`
```
{
    "email": "yerko@ejemplo.com",
    "password": "mi_password_seguro"
}
````
### 2. Registro de Tópico (`POST /topicos`)
Nota: El sistema valida que no existan duplicados con el mismo título y mensaje.

`JSON`
```
{
    "titulo": "Error en compilación Kotlin",
    "mensaje": "No reconoce el símbolo @Composable en mi proyecto.",
    "idUsuario": 1,
    "idCurso": 3
}
```
### 3. Registro de Respuesta (`POST /respuestas`)

`JSON`
````
{
    "mensaje": "Asegúrate de tener la dependencia de Compose en tu build.gradle.",
    "idTopico": 5,
    "idAutor": 1
}
````

### 4. Actualización de Tópico (`PUT /topicos/{id}`)

`JSON`
````
{
    "titulo": "Título actualizado",
    "mensaje": "Nuevo contenido del mensaje"
}
````
---

### 📌 Resumen de Cumplimiento Técnico
* **Formato**: Uso estricto de JSON para el intercambio de información.
* **Validación**: Implementación de `@RequestBody` y `@Valid` para asegurar la integridad de los datos.
* **Seguridad**: Los endpoints privados requieren el envío del Token JWT obtenido en el login.

---
## ⚠️ Gestión de Errores y Estados HTTP

La API implementa un control de excepciones centralizado para garantizar respuestas estandarizadas en formato JSON.

| Código HTTP | Escenario | Respuesta JSON |
| :--- | :--- | :--- |
| **400 Bad Request** | Datos de entrada inválidos o faltantes. | Lista de campos con sus errores de validación. |
| **403 Forbidden** | Token JWT ausente, inválido o expirado. | `{"error": "Acceso denegado..."}` |
| **404 Not Found** | El ID del recurso (Tópico/Respuesta) no existe. | Cuerpo vacío o mensaje de recurso no encontrado. |
| **409 Conflict** | Intento de registrar un tópico duplicado. | Mensaje de error de integridad de datos. |

### Ejemplo de Error de Validación (400)
`JSON`
````
  {
    "campo": "titulo",
    "mensaje": "no debe estar vacío"
  }
````
### Ejemplo de Error de Seguridad (403)
`JSON`
````
{
"error": "Acceso denegado. Token inválido, expirado o inexistente."
}
````
---

# ✨ Características Adicionales
* **Listado de Respuestas Dinámico:** Al consultar un tópico, obtienes automáticamente todas las respuestas asociadas gracias a la implementación de DTOs anidados.
* **Gestión de Errores Centralizada:** Implementación de GestorDeErrores que captura excepciones de validación (400) y entidades no encontradas (404), devolviendo mensajes JSON legibles para el frontend.
* **Reglas de Negocio Estrictas:** El sistema valida que solo el autor original del tópico pueda decidir qué respuesta marca como la solución definitiva.
* **Seguridad Stateless:** Protección total de la API sin uso de sesiones, optimizando el rendimiento para aplicaciones.
* **Respuestas Anidadas:** El detalle del tópico (GET /topicos/{id}) incluye automáticamente su lista de respuestas.
* **Documentación Swagger:** Interfaz gráfica para probar la API sin herramientas externas.
---
# 📖 Documentación Interactiva (Swagger)
Para visualizar y probar los endpoints en tiempo real, accede con el servidor en ejecución a:
👉 http://localhost:8080/swagger-ui.html 
---
# 👨‍💻Autor
**Yerko Osorio**

*Software Engineer & Android Developer (Kotlin/Compose)*

Proyecto desarrollado para el programa Oracle Next Education (ONE) - Alura Latam. 