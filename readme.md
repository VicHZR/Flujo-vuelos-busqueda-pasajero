# ✈️ Sistema de Microservicios: Flujo de Vuelos, Búsqueda y Pasajeros

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.4-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2023.0.3-blue.svg)](https://spring.io/projects/spring-cloud)
[![Maven](https://img.shields.io/badge/Maven-Multi--Module-red.svg)](https://maven.apache.org/)

Ecosistema backend distribuido de microservicios construido sobre **Spring Boot 3.3.4** y **Java 21**, estructurado bajo una arquitectura multi-módulo en Maven. El sistema ofrece una plataforma robusta para la gestión de usuarios, catálogo de vuelos y procesamiento de reservas de pasajeros, funcionando de manera **100% local y nativa** con configuraciones centralizadas mediante archivos YAML.

---

## 🏛️ Arquitectura y Flujo del Sistema

La arquitectura está diseñada bajo patrones de microservicios altamente desacoplados, donde las responsabilidades están estrictamente divididas:

```
                      [ Cliente / Bruno / Frontend ]
                                   │
                                   ▼
                        ┌─────────────────────┐
                        │   gateway-service   │ (Puerto 8594)
                        └──────────┬──────────┘
                                   │
         ┌─────────────────────────┼─────────────────────────┐
         ▼                         ▼                         ▼
┌─────────────────┐       ┌─────────────────┐       ┌─────────────────┐
│  auth-service   │       │ flight-service  │       │ booking-service │
│  (Puerto 8081)  │       │  (Puerto 8595)  │       │  (Puerto 2051)  │
└────────┬────────┘       └────────┬────────┘       └────────┬────────┘
         │                         │                         │
         └─────────────────────────┴─────────────────────────┘
                                   │
                                   ▼ (Descarga de configs YAML)
                        ┌─────────────────────┐
                        │    config-server    │ (Puerto 8888)
                        └─────────────────────┘
```

### Componentes Clave

1. **`config-server` (Puerto 8888):**
   * Servidor centralizado de configuración bajo el perfil `native`.
   * Sirve los archivos de configuración en formato `.yml` a todos los microservicios antes de su inicialización.

2. **`gateway-service` (Puerto 8594):**
   * Único punto de entrada (*Single Entry Point*) para los clientes.
   * Enruta peticiones dinámicamente hacia los microservicios internos, aislando la topología interna de la red.

3. **`auth-service` (Puerto 8081):**
   * Encargado del registro, inicio de sesión, generación y validación de tokens de seguridad JWT.

4. **`flight-service` (Puerto 8595):**
   * Administración del catálogo de vuelos, horarios, trayectos y disponibilidad de asientos.
   * Persistencia orientada a documentos mediante **MongoDB**.

5. **`booking-service` (Puerto 2051):**
   * Orquestación de reservas de pasajeros y estado de transacciones.
   * Persistencia relacional ACID mediante **MySQL**.

---

## 📋 Requisitos Previos

* **JDK 21** instalado y configurado en las variables de entorno (`JAVA_HOME`).
* **Apache Maven 3.8+** (o el wrapper del proyecto `mvnw`).
* **MySQL Server** ejecutándose localmente en el puerto `3306`.
* **MongoDB Community Server** ejecutándose localmente en el puerto `27017`.
* IDE preferido: **IntelliJ IDEA**.

---

## ⚙️ Configuración y Estructura

Cada microservicio consume sus variables de entorno e integraciones (URLs de bases de datos, credenciales, secretos JWT) directamente desde el `config-server` mediante archivos **YAML** jerárquicos (`application.yml`).

Asegúrate de tener creadas las bases de datos locales antes del arranque:
* MySQL: Base de datos para reservas (`booking_db`).
* MongoDB: Colección para vuelos (`flight_db`).

---

## 🚀 Guía de Despliegue Local (Terminal de IntelliJ)

### Paso 1: Compilar el proyecto raíz
Abre la terminal integrada de IntelliJ IDEA en la raíz del proyecto y compila todos los módulos:

```powershell
mvn clean compile
```

### Paso 2: Arranque secuencial de microservicios
Abre **5 pestañas independientes en la terminal de IntelliJ** (usando el botón `+`) y ejecuta los siguientes comandos en el orden indicado:

1. **Pestaña 1 — Config Server (Iniciar primero obligatoriamente):**
   ```powershell
   mvn -pl config-server spring-boot:run
   ```

2. **Pestaña 2 — Auth Service:**
   ```powershell
   mvn -pl auth-service spring-boot:run
   ```

3. **Pestaña 3 — Flight Service:**
   ```powershell
   mvn -pl flight-service spring-boot:run
   ```

4. **Pestaña 4 — Booking Service:**
   ```powershell
   mvn -pl booking-service spring-boot:run
   ```

5. **Pestaña 5 — Gateway Service:**
   ```powershell
   mvn -pl gateway-service spring-boot:run
   ```

---

## 🛠️ Solución de Problemas Comunes

### Puerto en uso (ej. `Port 8888 was already in use`)
Si por algún cierre inesperado un puerto queda bloqueado en Windows/PowerShell, libera el proceso con el siguiente comando:

```powershell
Get-NetTCPConnection -LocalPort 8888 -ErrorAction SilentlyContinue | Select-Object -ExpandProperty OwningProcess | ForEach-Object { Stop-Process -Id $_ -Force }
```

---

## 👨‍💻 Autor

* **Víctor Hugo Guzmán Prieto** - [@VicHZR](https://github.com/VicHZR)