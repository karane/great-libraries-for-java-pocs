package org.karane.mapstruct.model;

public class PhoneNumber {

    private String value;

    public PhoneNumber() {}

    public PhoneNumber(String value) {
        this.value = value;
    }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}
