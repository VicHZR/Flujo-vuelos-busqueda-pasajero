package primer.intento.time.passenger.domain.model;

public class Passenger {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String documentNumber;

    public Passenger(Long id, String firstName, String lastName, String email, String documentNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.documentNumber = documentNumber;
    }

    // Getters y métodos de negocio si aplican
    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getDocumentNumber() { return documentNumber; }
}