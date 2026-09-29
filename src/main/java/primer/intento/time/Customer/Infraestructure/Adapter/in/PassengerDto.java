package primer.intento.time.Customer.Infraestructure.Adapter.in;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PassengerDto {

    private Long id;

    @NotBlank
    private String firstname;

    @NotBlank
    private String secondname;

    @NotBlank
    private String lastname;

    @NotBlank
    private String tipeId;

    @NotNull
    private Integer age;

    @NotNull
    private Integer numberId;

    @NotBlank
    private String airline;

    @NotNull
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