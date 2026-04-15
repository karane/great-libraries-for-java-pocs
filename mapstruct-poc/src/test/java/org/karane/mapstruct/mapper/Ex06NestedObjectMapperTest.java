package org.karane.mapstruct.mapper;

import org.junit.jupiter.api.Test;
import org.karane.mapstruct.dto.PersonWithAddressDTO;
import org.karane.mapstruct.model.Address;
import org.karane.mapstruct.model.Person;
import org.karane.mapstruct.model.PhoneNumber;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class Ex06NestedObjectMapperTest {

    private final Ex06NestedObjectMapper mapper = Ex06NestedObjectMapper.INSTANCE;

    private Person person() {
        return new Person(42L, "Bob", "Jones", "bob@example.com",
                LocalDate.of(1990, 1, 1),
                new Address("5 Maple Rd", "Denver", "CO", "80201"),
                new PhoneNumber("+1-000-000-0000"));
    }

    @Test
    void toDTO_delegatesNestedAddressMapping() {
        PersonWithAddressDTO dto = mapper.toDTO(person());
        assertNotNull(dto.getAddress());
        // Ex02FieldRenameMapper applied: zipCode -> postalCode
        assertEquals("80201", dto.getAddress().getPostalCode());
        assertEquals("5 Maple Rd", dto.getAddress().getStreet());
    }

    @Test
    void toDTO_mapsTopLevelFields() {
        PersonWithAddressDTO dto = mapper.toDTO(person());
        assertEquals(42L, dto.getId());
        assertEquals("bob@example.com", dto.getEmail());
    }
}
