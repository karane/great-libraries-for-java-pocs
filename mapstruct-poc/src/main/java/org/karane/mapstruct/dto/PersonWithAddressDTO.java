package org.karane.mapstruct.dto;

public class PersonWithAddressDTO {

    private Long id;
    private String email;
    private AddressDTO address;

    public PersonWithAddressDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public AddressDTO getAddress() { return address; }
    public void setAddress(AddressDTO address) { this.address = address; }
}
