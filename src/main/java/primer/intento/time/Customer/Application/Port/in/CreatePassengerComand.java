package primer.intento.time.Customer.Application.Port.in;

public class CreatePassengerComand {

    String name;
    String secondname;
    String lastname;
    String typeId;
    Integer numberId;
    Integer flightNumber;
    String email;
    String phone;

    public CreatePassengerComand(String name, String secondname,String lastname
            ,String typeId, Integer numberId ,Integer flightNumber ,String email, String phone) {

        this.name = name;
        this.secondname = secondname;
        this.lastname = lastname;
        this.email = email;
        this.typeId = typeId;
        this.numberId = numberId;
        this.flightNumber = flightNumber;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getNumberId() {
        return numberId;
    }

    public void setNumberId(Integer numberId) {
        this.numberId = numberId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(Integer flightNumber) {
        this.flightNumber = flightNumber;
    }
}
