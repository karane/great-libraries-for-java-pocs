package org.karane.mapstruct.dto;

public class AddressDTO {

    private String street;
    private String city;
    // "state" is mapped from Address.state (same name, no annotation needed)
    private String state;
    // "postalCode" is mapped from Address.zipCode (different name — needs @Mapping)
    private String postalCode;

    public AddressDTO() {}

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
}
