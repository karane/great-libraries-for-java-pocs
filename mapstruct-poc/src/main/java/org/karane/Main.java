package org.karane;

import org.karane.mapstruct.dto.*;
import org.karane.mapstruct.mapper.*;
import org.karane.mapstruct.model.*;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Address address = new Address("123 Main St", "Springfield", "IL", "62701");
        PhoneNumber phone = new PhoneNumber("+1-217-555-0100");
        Person person = new Person(1L, "John", "Doe", "john@example.com",
                LocalDate.of(1990, 5, 15), address, phone);
        Employee employee = new Employee(2L, "Alice", "Brown", "alice@example.com",
                LocalDate.of(1995, 8, 22), address, phone, "Engineering");

        PersonSameNameDTO ex01 = Ex01SameNameMapper.INSTANCE.toDTO(person);
        System.out.println("Ex01 id=" + ex01.getId() + " email=" + ex01.getEmail());

        AddressDTO ex02 = Ex02FieldRenameMapper.INSTANCE.toDTO(address);
        System.out.println("Ex02 postalCode=" + ex02.getPostalCode());

    }
}
