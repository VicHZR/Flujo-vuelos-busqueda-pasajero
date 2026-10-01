package primer.intento.time.Customer.Infraestructure.Entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity(name="Passenger")
@Table(name="passenger")
public class PassengerEnitity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name="first_name", nullable = false)
    private String firstname;

    @NotBlank
    @Column(name = "second_name", nullable = false)
    private String secondname;

    @NotBlank
    @Column(name="last_name", nullable = false)
    private String lastname;

    @NotBlank
    @Column(name="type_id", nullable = false)
    private String typeId;

    @NotNull
    @Column(name = "age", nullable = false)
    private Integer age;

    @NotNull
    @Column(name ="numeber_id", nullable = false)
    private Integer numberId;

    @NotBlank
    @Column(name = "airline", nullable = false)
    private String airline;

    @NotNull
    @Column(name = "flight_number", nullable = false)
    private Integer flightNumber;

    @NotBlank
    @Column (name = "email", nullable = false)
    private String email;

    @NotNull
    @Column(name = "phone",nullable = false)
    private Integer phone;

    public PassengerEnitity(){}

    public PassengerEnitity(String firstname, String secondname, String lastname, String typeId,
                            Integer age, Integer numberId, String airline, Integer flightNumber,
                            String email, Integer phone){
        this.firstname = firstname;
        this.secondname = secondname;
        this.lastname = lastname;
        this.typeId = typeId;
        this.age = age;
        this.numberId = numberId;
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.email = email;
        this.phone = phone;

    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getFirstname() {return firstname;}

    public void setFirstname(String firstname) {this.firstname = firstname;}

    public String getSecondname() {return secondname;}

    public void setSecondname(String secondname) {this.secondname = secondname;}

    public String getLastname() {return lastname;}

    public void setLastname(String lastname) {this.lastname = lastname;}

    public String getTypeId() {return typeId;}

    public void setTypeId(String typeId) {this.typeId = typeId;}

    public Integer getAge() {return age;}

    public void setAge(Integer age) {this.age = age;}

    public Integer getNumberId() {return numberId;}

    public void setNumberId(Integer numberId) {this.numberId = numberId;}

    public String getAirline() {return airline;}

    public void setAirline(String airline) {this.airline = airline;}

    public Integer getFlightNumber() {return flightNumber;}

    public void setFlightNumber(Integer flightNumber) {this.flightNumber = flightNumber;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public Integer getPhone() {return phone;}

public void setPhone(Integer phone) {this.phone = phone;}
}