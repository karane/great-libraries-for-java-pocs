package org.karane.mapstruct.model;

import java.time.LocalDate;

public class Employee extends Person {

    private String department;

    public Employee() {}

    public Employee(Long id, String firstName, String lastName, String email,
                    LocalDate birthDate, Address address, PhoneNumber phoneNumber, String department) {
        super(id, firstName, lastName, email, birthDate, address, phoneNumber);
        this.department = department;
    }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}
