package camper.model;

public class Customer {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final String phoneNo;
    private final String countryName;
    private final String cityPostalCode;
    private final String cityName;
    private final String street;
    private final String aptNumber;
    private final String floor;

    public Customer(int id, String firstName, String lastName, String phoneNo, String countryName, String cityPostalCode, String cityName, String street, String aptNumber, String floor) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.countryName = countryName;
        this.cityPostalCode = cityPostalCode;
        this.cityName = cityName;
        this.street = street;
        this.aptNumber = aptNumber;
        this.floor = floor;
    }

    public int getId() {
        return id;
    }
    public String getIdToString() {
        return String.valueOf(id);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCityPostalCode() {
        return cityPostalCode;
    }

    public String getCityName() {
        return cityName;
    }

    public String getStreet() {
        return street;
    }

    public String getAptNumber() {
        return aptNumber;
    }

    public String getFloor() {
        return floor;
    }

    public String getAddress(){
        return cityPostalCode+", "+cityName+", "+street+", "+aptNumber+", "+floor;
    }



    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", countryName='" + countryName + '\'' +
                ", cityPostalCode='" + cityPostalCode + '\'' +
                ", cityName='" + cityName + '\'' +
                ", street='" + street + '\'' +
                ", aptNumber='" + aptNumber + '\'' +
                ", floor='" + floor + '\'' +
                '}';
    }
}