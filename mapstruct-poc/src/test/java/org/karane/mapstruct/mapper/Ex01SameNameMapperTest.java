package org.karane.mapstruct.mapper;

import org.junit.jupiter.api.Test;
import org.karane.mapstruct.dto.PersonSameNameDTO;
import org.karane.mapstruct.model.Address;
import org.karane.mapstruct.model.Person;
import org.karane.mapstruct.model.PhoneNumber;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Ex01SameNameMapperTest {

    private final Ex01SameNameMapper mapper = Ex01SameNameMapper.INSTANCE;

    private Person person() {
        return new Person(1L, "John", "Doe", "john@example.com",
                LocalDate.of(1990, 1, 1),
                new Address("1 St", "City", "ST", "00001"),
                new PhoneNumber("+1-000-000-0000"));
    }

    @Test
    void toDTO_mapsId() {
        PersonSameNameDTO dto = mapper.toDTO(person());
        assertEquals(1L, dto.getId());
    }

    @Test
    void toDTO_mapsEmail() {
        PersonSameNameDTO dto = mapper.toDTO(person());
        assertEquals("john@example.com", dto.getEmail());
    }
}
