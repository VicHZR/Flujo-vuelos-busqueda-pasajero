package primer.intento.time.domain.model;

public class Passenger {
    private Long id;
    private String firstName;
    private String lastName;
    private String documentType;
    private String documentNumber;
    private int age;

    public Passenger(Long id, String firstName, String lastName, String documentType, String documentNumber, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.age = age;
    }

    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getDocumentType() { return documentType; }
    public String getDocumentNumber() { return documentNumber; }
    public int getAge() { return age; }
}
