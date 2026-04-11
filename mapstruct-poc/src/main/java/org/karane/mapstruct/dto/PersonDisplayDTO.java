package org.karane.mapstruct.dto;

public class PersonDisplayDTO {

    private String fullName;
    private int birthYear;

    public PersonDisplayDTO() {}

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public int getBirthYear() { return birthYear; }
    public void setBirthYear(int birthYear) { this.birthYear = birthYear; }
}
