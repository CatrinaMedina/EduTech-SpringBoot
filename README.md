# 🎓 EduTech Spring Boot

Aplicación web desarrollada con **Spring Boot** para la gestión de una plataforma educativa.

El proyecto permite administrar cursos, descuentos, usuarios, gerentes, feedback e incidencias mediante una interfaz web desarrollada con **Thymeleaf** y una **API REST** para las operaciones CRUD.

## 🚀 Tecnologías

- Java 21
- Spring Boot 3.5.0
- Spring Web
- Spring Data JPA
- Thymeleaf
- Spring Boot Actuator
- MySQL
- Maven
- Swagger / OpenAPI
- JUnit 5
- Mockito

## 📁 Estructura del proyecto

```text
EduTech-SpringBoot/
│
├── Bases/
│   ├── basecurso.sql
│   ├── basediscount.sql
│   ├── basefeedback.sql
│   ├── basegerente.sql
│   ├── baseincidencia.sql
│   └── baseusuario.sql
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/edutech/ProyectoFullstack/
│   │   │       ├── controllers/
│   │   │       ├── entities/
│   │   │       ├── repositories/
│   │   │       ├── restcontroller/
│   │   │       └── services/
│   │   │
│   │   └── resources/
│   │       ├── templates/
│   │       │   ├── curso.html
│   │       │   ├── discount.html
│   │       │   ├── feedback.html
│   │       │   ├── gerente.html
│   │       │   ├── incidencia.html
│   │       │   └── usuario.html
│   │       └── application.properties
│   │
│   └── test/
│       └── java/
│
├── .mvn/
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

## ✨ Funcionalidades

### 📚 Gestión de cursos

Permite:

- Crear cursos
- Listar cursos
- Consultar un curso por ID
- Actualizar cursos
- Eliminar cursos

### 💰 Gestión de descuentos

Permite:

- Crear descuentos
- Listar descuentos
- Consultar descuentos por ID
- Actualizar descuentos
- Eliminar descuentos

### 👤 Gestión de usuarios

Permite:

- Crear usuarios
- Listar usuarios
- Consultar usuarios por ID
- Actualizar usuarios
- Eliminar usuarios

### 👨‍💼 Gestión de gerentes

Permite:

- Crear gerentes
- Listar gerentes
- Consultar gerentes por ID
- Actualizar gerentes
- Eliminar gerentes

### 💬 Gestión de feedback

Permite:

- Registrar feedback
- Listar feedback
- Consultar feedback por ID
- Actualizar feedback
- Eliminar feedback

### ⚠️ Gestión de incidencias

Permite:

- Registrar incidencias
- Listar incidencias
- Consultar incidencias por ID
- Actualizar incidencias
- Eliminar incidencias

## 🏗️ Arquitectura

El proyecto utiliza una arquitectura por capas:

```text
              Cliente
                 │
                 ▼
      ┌─────────────────────┐
      │ Controller / REST   │
      │      Controller     │
      └──────────┬──────────┘
                 │
                 ▼
      ┌─────────────────────┐
      │       Service       │
      └──────────┬──────────┘
                 │
                 ▼
      ┌─────────────────────┐
      │     Repository      │
      └──────────┬──────────┘
                 │
                 ▼
      ┌─────────────────────┐
      │       Entity        │
      └──────────┬──────────┘
                 │
                 ▼
              MySQL
```

### Capas principales

**Controllers**

Manejan las solicitudes de las vistas web y coordinan las operaciones de la aplicación.

**REST Controllers**

Exponen los endpoints de la API REST.

**Services**

Contienen la lógica de negocio.

**Repositories**

Utilizan Spring Data JPA para interactuar con la base de datos.

**Entities**

Representan las entidades persistidas en MySQL.

**Templates**

Contienen las vistas HTML desarrolladas con Thymeleaf.

## 🗄️ Base de datos

El proyecto utiliza **MySQL**.

Configuración actual:

```text
Host: localhost
Puerto: 3307
Base de datos: baseedutech
Usuario: root
```

La configuración se encuentra en:

```text
src/main/resources/application.properties
```

Configuración utilizada:

```properties
spring.datasource.url=jdbc:mysql://localhost:3307/baseedutech?serverTimezone=UTC&useSSL=false
spring.datasource.username=root

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format-sql=true

springdoc.swagger-ui.path=/swagger-ui.html
server.port=8080
```

> Ajusta el usuario, contraseña, puerto o nombre de la base de datos según tu entorno local.

## 📂 Scripts SQL

Los scripts de la base de datos se encuentran en:

```text
Bases/
```

Archivos:

```text
basecurso.sql
basediscount.sql
basefeedback.sql
basegerente.sql
baseincidencia.sql
baseusuario.sql
```

## ⚙️ Requisitos

Antes de ejecutar el proyecto debes tener instalado:

- Java 21
- MySQL
- Git

Maven no necesita instalarse globalmente porque el proyecto incluye **Maven Wrapper**.

## ▶️ Instalación

Clonar el repositorio:

```bash
git clone https://github.com/CatrinaMedina/EduTech-SpringBoot.git
```

Entrar al proyecto:

```bash
cd EduTech-SpringBoot
```

Crear la base de datos:

```sql
CREATE DATABASE baseedutech;
```

Configurar la conexión en:

```text
src/main/resources/application.properties
```

## ▶️ Ejecutar la aplicación

### Windows

```bash
mvnw.cmd spring-boot:run
```

### Linux / macOS

```bash
./mvnw spring-boot:run
```

La aplicación quedará disponible en:

```text
http://localhost:8080
```

## 🌐 Vistas web

La aplicación incluye vistas Thymeleaf para:

```text
/cursos
/discounts
/feedbacks
/gerentes
/incidencias
/usuarios
```

Cada sección permite interactuar con las operaciones correspondientes desde la interfaz web.

## 🔌 API REST

### Cursos

```http
GET    /api/cursos
GET    /api/cursos/{id}
POST   /api/cursos
PUT    /api/cursos/{id}
DELETE /api/cursos/{id}
```

### Descuentos

```http
GET    /api/discounts
GET    /api/discounts/{id}
POST   /api/discounts
PUT    /api/discounts/{id}
DELETE /api/discounts/{id}
```

### Feedback

```http
GET    /api/feedbacks
GET    /api/feedbacks/{id}
POST   /api/feedbacks
PUT    /api/feedbacks/{id}
DELETE /api/feedbacks/{id}
```

### Gerentes

```http
GET    /api/gerentes
GET    /api/gerentes/{id}
POST   /api/gerentes
PUT    /api/gerentes/{id}
DELETE /api/gerentes/{id}
```

### Incidencias

```http
GET    /api/incidencias
GET    /api/incidencias/{id}
POST   /api/incidencias
PUT    /api/incidencias/{id}
DELETE /api/incidencias/{id}
```

### Usuarios

```http
GET    /api/usuarios
GET    /api/usuarios/{id}
POST   /api/usuarios
PUT    /api/usuarios/{id}
DELETE /api/usuarios/{id}
```

## 📖 Swagger / OpenAPI

La documentación de la API se puede visualizar mediante Swagger.

Con la aplicación ejecutándose:

```text
http://localhost:8080/swagger-ui.html
```

Documentación OpenAPI:

```text
http://localhost:8080/v3/api-docs
```

Desde Swagger puedes probar directamente los endpoints REST.

## 🧪 Testing

El proyecto incluye pruebas automatizadas para los servicios y controladores REST.

Se incluyen pruebas para:

- Cursos
- Descuentos
- Feedback
- Gerentes
- Incidencias
- Usuarios
- Contexto principal de Spring Boot

### Ejecutar pruebas en Windows

```bash
mvnw.cmd test
```

### Ejecutar pruebas en Linux / macOS

```bash
./mvnw test
```

## 📦 Generar build

### Windows

```bash
mvnw.cmd clean package
```

### Linux / macOS

```bash
./mvnw clean package
```

El archivo generado quedará dentro de:

```text
target/
```

## 🔄 Flujo de la aplicación

```text
Usuario
   │
   ▼
Interfaz Thymeleaf
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
MySQL
```

Para consumo externo:

```text
Cliente
   │
   ▼
API REST
   │
   ▼
REST Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
MySQL
```

## 📌 Estado del proyecto

El proyecto cuenta con:

- CRUD de cursos
- CRUD de descuentos
- CRUD de usuarios
- CRUD de gerentes
- CRUD de feedback
- CRUD de incidencias
- Vistas Thymeleaf
- Persistencia con JPA/Hibernate
- API REST
- Documentación Swagger/OpenAPI
- Pruebas automatizadas

## 👩‍💻 Proyecto

**EduTech Spring Boot**
