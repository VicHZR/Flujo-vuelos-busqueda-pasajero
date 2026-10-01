package primer.intento.time.Customer.Infraestructure.Adapter.in;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PassengerDto {

    private Long id;

    @NotBlank(message = "El primer nombre no puede estar vacio")
    private String firstname;

    @NotBlank (message = "El segundo nombre no puede estar vacio")
    private String secondname;

    @NotBlank (message = "El apellido no puede estar vacio")
    private String lastname;

    @NotBlank(message = "El tipo de documento no puede estar vacio")
    private String tipeId;

    @NotNull(message = "La edad es obligatoria")
    @Positive(message = "La edad tiene que ser mayor a 0")
    private Integer age;

    @NotNull(message = "No puede estar vacio")
    @Positive(message = "El numero del documento tiene que ser mayor a 0")
    private Integer numberId;

    @NotBlank(message = "El campo no debe estar vacio")
    private String airline;

    @NotNull(message = "")
    @Positive(message = "El numero del vuelo debe ser mayor a 0")
    private Integer flightNumber;

    @NotBlank
    @Email(message = "El email no tiene formato valido")
    private String email;

    @NotBlank
    private Integer phone;

    public PassengerDto() {}

    public PassengerDto(Long id, String firstname, String secondname, String lastname, String tipeId,
                        Integer age, Integer numberId, String airline, Integer flightNumber, String email,
                        Integer phone) {
        this.id = id;
        this.firstname = firstname;
        this.secondname = secondname;
        this.lastname = lastname;
        this.tipeId = tipeId;
        this.age = age;
        this.numberId = numberId;
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.email = email;
        this.phone = phone;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTipeId() {
        return tipeId;
    }

    public void setTipeId(String tipeId) {
        this.tipeId = tipeId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getNumberId() {
        return numberId;
    }

    public void setNumberId(Integer numberId) {
        this.numberId = numberId;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public Integer getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(Integer flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }
}