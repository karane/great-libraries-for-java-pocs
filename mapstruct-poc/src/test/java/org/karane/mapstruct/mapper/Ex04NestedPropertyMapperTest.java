package org.karane.mapstruct.mapper;

import org.junit.jupiter.api.Test;
import org.karane.mapstruct.dto.PersonContactDTO;
import org.karane.mapstruct.model.Address;
import org.karane.mapstruct.model.Person;
import org.karane.mapstruct.model.PhoneNumber;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class Ex04NestedPropertyMapperTest {

    private final Ex04NestedPropertyMapper mapper = Ex04NestedPropertyMapper.INSTANCE;

    private Person person(PhoneNumber phone) {
        return new Person(1L, "Jane", "Doe", "jane@example.com",
                LocalDate.of(1990, 1, 1),
                new Address("1 St", "City", "ST", "00001"),
                phone);
    }

    @Test
    void toDTO_accessesNestedPhoneValue() {
        PersonContactDTO dto = mapper.toDTO(person(new PhoneNumber("+1-312-555-0100")));
        assertEquals("+1-312-555-0100", dto.getPhoneNumber());
    }

    @Test
    void toDTO_nullPhoneNumber_mapsToNull() {
        PersonContactDTO dto = mapper.toDTO(person(null));
        assertNull(dto.getPhoneNumber());
    }
}
