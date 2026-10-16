package primer.intento.time.infraestructure.adapter.in.web.dto;

public class PassengerDto {
    private String firstName;
    private String lastName;
    private String documentType;
    private String documentNumber;
    private int age;

    public PassengerDto(String firstName, String lastName, String documentType, String documentNumber, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.age = age;
    }

    public PassengerDto() {}

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