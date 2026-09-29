package primer.intento.time.Customer.Domain.Model;



public class Passenger {

    private Long id;
    private String firstname;
    private String secondname;
    private String lastname;
    private String typeId;
    private Integer age;
    private Integer numberId;
    private String airline;
    private Integer flightNumber;
    private String email;
    private Integer phone;

    public Passenger(){}

    public Passenger(Long id, String firstname, String secondname, String lastname, String typeId,
                     Integer age, Integer numberId, String airline, Integer flightNumber,
                     String email, Integer phone) {
        this.id = id;
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

    public Long getId(Integer numberId) {
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

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
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

    public Integer getFlightNumber(Integer flightNumber) {
        return this.flightNumber;
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
