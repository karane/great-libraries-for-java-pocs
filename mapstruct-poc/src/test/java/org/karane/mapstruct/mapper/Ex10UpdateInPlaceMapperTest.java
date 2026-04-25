package org.karane.mapstruct.mapper;

import org.junit.jupiter.api.Test;
import org.karane.mapstruct.dto.AddressDTO;
import org.karane.mapstruct.dto.PersonUpdateDTO;
import org.karane.mapstruct.model.Address;
import org.karane.mapstruct.model.Person;
import org.karane.mapstruct.model.PhoneNumber;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Ex10UpdateInPlaceMapperTest {

    private final Ex10UpdateInPlaceMapper mapper = Ex10UpdateInPlaceMapper.INSTANCE;

    private Person person() {
        return new Person(99L, "Alice", "Wonder", "alice@example.com",
                LocalDate.of(1990, 6, 15),
                new Address("1 Old St", "OldCity", "OC", "00001"),
                new PhoneNumber("+1-000-000-0000"));
    }

    private PersonUpdateDTO updateDTO() {
        AddressDTO newAddress = new AddressDTO();
        newAddress.setStreet("2 New St");
        newAddress.setCity("NewCity");
        newAddress.setState("NC");
        newAddress.setPostalCode("99999");

        PersonUpdateDTO dto = new PersonUpdateDTO();
        dto.setEmail("alice.new@example.com");
        dto.setAddress(newAddress);
        return dto;
    }

    @Test
    void update_changesEmail() {
        Person person = person();
        mapper.update(updateDTO(), person);
        assertEquals("alice.new@example.com", person.getEmail());
    }

    @Test
    void update_changesAddress() {
        Person person = person();
        mapper.update(updateDTO(), person);
        assertEquals("2 New St", person.getAddress().getStreet());
        // Ex03ReverseMappingMapper restores postalCode -> zipCode
        assertEquals("99999", person.getAddress().getZipCode());
    }

    @Test
    void update_doesNotOverwriteId() {
        Person person = person();
        mapper.update(updateDTO(), person);
        assertEquals(99L, person.getId());
    }

    @Test
    void update_doesNotOverwriteName() {
        Person person = person();
        mapper.update(updateDTO(), person);
        assertEquals("Alice", person.getFirstName());
        assertEquals("Wonder", person.getLastName());
    }

    @Test
    void update_doesNotOverwriteBirthDate() {
        Person person = person();
        mapper.update(updateDTO(), person);
        assertEquals(LocalDate.of(1990, 6, 15), person.getBirthDate());
    }
}
