package org.karane.mapstruct.dto;

public class PersonUpdateDTO {

    private String email;
    private AddressDTO address;

    public PersonUpdateDTO() {}

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public AddressDTO getAddress() { return address; }
    public void setAddress(AddressDTO address) { this.address = address; }
}
