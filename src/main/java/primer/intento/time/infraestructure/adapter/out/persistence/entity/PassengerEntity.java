package primer.intento.time.infraestructure.adapter.out.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "passengers")
public class PassengerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String documentType;
    private String documentNumber;
    private int age;

    // Generar constructores vacíos, getters y setters en tu IDE
    public PassengerEntity() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getDocumentType() { return documentType; }
    public void setDocumentType(String documentType) { this.documentType = documentType; }
    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}
