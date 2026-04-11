package org.karane.mapstruct.dto;

public class EmployeeBaseDTO extends PersonSameNameDTO {

    private String department;

    public EmployeeBaseDTO() {}

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}
