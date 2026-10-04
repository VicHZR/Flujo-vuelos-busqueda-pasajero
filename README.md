# ✈️ Travel & Lodging Reservation API

Una API RESTful robusta y escalable desarrollada en **Java y Spring Boot**, orientada a la gestión integral de reservas de alojamiento, pasajeros y boletos de vuelo. 

Este proyecto ha sido diseñado bajo los estrictos principios de la **Arquitectura Hexagonal (Puertos y Adaptadores)**, garantizando un bajo acoplamiento, alta cohesión y una clara separación de responsabilidades entre la lógica de negocio central y las dependencias de infraestructura externa (bases de datos, web framework).

## 🚀 Tecnologías Utilizadas

*   **Lenguaje:** Java
*   **Framework:** Spring Boot
*   **Persistencia:** Spring Data JPA / Hibernate
*   **Arquitectura:** Hexagonal (Ports and Adapters)
*   **Formato de datos:** JSON (Jackson)

## 🏗️ Estructura de la Arquitectura Hexagonal

El proyecto está dividido en tres capas principales, respetando la regla de dependencia (de afuera hacia adentro):

1.  **Domain (Core):** Contiene los modelos de negocio puros (`LodgingReservation`, `Passenger`, `FlightTicket`). Estas clases son **agnósticas** a cualquier framework o tecnología externa.
2.  **Application:** Contiene la lógica de orquestación. Define los Casos de Uso (`ManageReservationUseCase`) y los **Puertos**:
    *   *Inbound Ports:* Interfaces que definen cómo el exterior se comunica con el dominio.
    *   *Outbound Ports:* Interfaces (`ReservationRepositoryPort`) que definen cómo el dominio se comunica con el exterior.
3.  **Infrastructure:** Implementa los adaptadores que conectan el exterior con la aplicación.
    *   *Inbound Adapters (Web):* Controladores REST (`ReservationController`).
    *   *Outbound Adapters (Persistence):* Implementación de repositorios (`ReservationJpaAdapter`), Entidades de base de datos de Spring Data (`LodgingReservationEntity`, etc.) y sus respectivos mapeadores al dominio.

## 🛠️ Decisiones de Diseño y Buenas Prácticas

*   **Separación Modelos vs Entidades:** Los objetos de dominio y las entidades JPA están estrictamente separados. Se utilizan mapeadores (`toDomain()`) en el adaptador de persistencia para evitar que las anotaciones de base de datos contaminen la lógica de negocio.
*   **Actualizaciones Parciales (PATCH):** Se implementó un soporte sólido para peticiones `PATCH`, permitiendo actualizar campos específicos (como cambiar solo el tipo de alojamiento) sin sobreescribir ni anular las entidades relacionadas (Pasajeros o Vuelos).
*   **Prevención de Referencias Cíclicas:** Se manejó la bidireccionalidad entre `FlightTicket` y `Passenger` utilizando la anotación `@JsonIgnore` a nivel de dominio, garantizando respuestas JSON limpias y evitando errores de `StackOverflow` durante la serialización.

## 📡 Endpoints de la API

La API expone los siguientes endpoints bajo la ruta base `/api/reservations`:

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `POST` | `/api/reservations` | Crea una nueva reserva completa. |
| `GET` | `/api/reservations` | Retorna el listado de todas las reservas. |
| `GET` | `/api/reservations/{id}` | Retorna los detalles de una reserva específica. |
| `PUT` | `/api/reservations/{id}` | Actualiza de forma completa una reserva. |
| `PATCH` | `/api/reservations/{id}` | Actualiza parcialmente una reserva (ej. solo el alojamiento). |
| `DELETE` | `/api/reservations/{id}` | Elimina una reserva de la base de datos. |

### 📝 Ejemplo de Payload (Respuesta JSON)

**GET** `/api/reservations/10`

```json
{
    "id": 10,
    "accommodationType": "Airbnb - Valle Sagrado",
    "passenger": {
      "id": 10,
      "firstName": "Eunice Fiorella",
      "lastName": "Ancajima",
      "documentType": "DNI",
      "documentNumber": "12345678",
      "age": 30
    },
    "flightTicket": {
      "id": 10,
      "ticketNumber": "TK-8472",
      "airline": "JetSMART",
      "seatNumber": "12A",
      "flightNumber": "JA-402",
      "flightTime": "2026-10-20T08:00:00"
    }
}
```
## ⚙️ Instalación y Ejecución local
Clona el repositorio:

git clone [https://github.com/tu-usuario/travel-reservation-api.git](https://github.com/tu-usuario/travel-reservation-api.git)

Navega al directorio del proyecto:

cd travel-reservation-api

Configura tus credenciales de base de datos en el archivo application.properties o application.yml.

Ejecuta la aplicación usando Maven o tu IDE preferido:

./mvnw spring-boot:run
