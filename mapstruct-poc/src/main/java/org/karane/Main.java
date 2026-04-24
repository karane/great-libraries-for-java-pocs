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

        Address ex03 = Ex03ReverseMappingMapper.INSTANCE.toModel(ex02);
        System.out.println("Ex03 zipCode=" + ex03.getZipCode());

        PersonContactDTO ex04 = Ex04NestedPropertyMapper.INSTANCE.toDTO(person);
        System.out.println("Ex04 phoneNumber=" + ex04.getPhoneNumber());

        PersonDisplayDTO ex05 = Ex05ExpressionMapper.INSTANCE.toDTO(person);
        System.out.println("Ex05 fullName=" + ex05.getFullName() + " birthYear=" + ex05.getBirthYear());

        PersonWithAddressDTO ex06 = Ex06NestedObjectMapper.INSTANCE.toDTO(person);
        System.out.println("Ex06 postalCode=" + ex06.getAddress().getPostalCode());

        List<PersonDisplayDTO> ex07 = Ex07CollectionMapper.INSTANCE.toDTOList(List.of(person, person));
        System.out.println("Ex07 listSize=" + ex07.size());

        PersonContactDTO ex08 = Ex08CustomConverterMapper.INSTANCE.toDTO(person);
        System.out.println("Ex08 phoneNumber=" + ex08.getPhoneNumber());

        PersonSameNameDTO ex09person = Ex09SubclassMapper.INSTANCE.toDTO(person);
        PersonSameNameDTO ex09employee = Ex09SubclassMapper.INSTANCE.toDTO(employee);
        System.out.println("Ex09 person isEmployee=" + (ex09person instanceof EmployeeBaseDTO));
        System.out.println("Ex09 employee isEmployee=" + (ex09employee instanceof EmployeeBaseDTO));
        System.out.println("Ex09 department=" + ((EmployeeBaseDTO) ex09employee).getDepartment());

    }
}
