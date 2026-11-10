# ✈️ Sistema de Microservicios: Flujo de Vuelos, Búsqueda y Pasajeros

Plataforma backend distribuida desarrollada en **Java 21** y **Spring Boot 3.3.4**, estructurada bajo un modelo multi-módulo de Maven para garantizar la separación de responsabilidades, alta cohesión y bajo acoplamiento entre los servicios de negocio.

---

## 🛠️ Arquitectura del Sistema

El ecosistema se compone de los siguientes módulos independientes que se comunican de forma centralizada:

| Módulo | Puerto | Descripción | Tecnologías Clave |
| :--- | :---: | :--- | :--- |
| **`config-server`** | `8888` | Servidor de configuración centralizada (perfil `native`). | Spring Cloud Config Server |
| **`gateway-service`** | `8594` | API Gateway perimetral y punto único de entrada. | Spring Cloud Gateway |
| **`auth-service`** | `8081` | Microservicio de autenticación, control de identidad y roles. | Spring Security, JWT |
| **`flight-service`** | `8595` | Gestión y catálogo de vuelos, rutas y disponibilidad. | MongoDB, Spring Data |
| **`booking-service`** | `2051` | Lógica central de transacciones y procesamiento de reservas. | MySQL, Spring Data JPA |

---

## 🚀 Guía de Ejecución Local

Este proyecto está configurado para ejecutarse de manera **100% nativa y local** utilizando la terminal integrada de tu IDE (como IntelliJ IDEA) y el ciclo de vida de Maven, sin dependencia de contenedores para la ejecución de los servicios.

### 1. Prerrequisitos
* **Java Development Kit (JDK) 21** instalado y configurado en el sistema.
* **MySQL** y **MongoDB** corriendo de manera local en sus puertos por defecto (`3306` y `27017`).

### 2. Compilación del Proyecto
Posiciónate en la carpeta raíz del proyecto multi-módulo y ejecuta el comando de limpieza y compilación:
```powershell
mvn clean compile
```
```Paso 1: Iniciar el Servidor de Configuración
mvn -pl config-server spring-boot:run
```
```Paso 2: Iniciar el Servicio de Autenticación
mvn -pl auth-service spring-boot:run
```
```Paso 3: Iniciar el Servicio de Vuelos
mvn -pl flight-service spring-boot:run
```
```Paso 4: Iniciar el Servicio de Reservas
mvn -pl booking-service spring-boot:run
```
```Paso 5: Iniciar el API Gateway
mvn -pl gateway-service spring-boot:run
```
```🧪 Pruebas de Endpoints
Puedes utilizar herramientas como Bruno o Postman para realizar peticiones HTTP apuntando directamente al puerto del API Gateway (http://localhost:8594), el cual se encargará de enrutar las solicitudes de forma transparente hacia los microservicios correspondientes.

👨‍💻 Autor
Víctor Hugo Guzmán Prieto (@VicHZR)

