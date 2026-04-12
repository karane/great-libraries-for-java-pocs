package org.karane.mapstruct.mapper;

import org.junit.jupiter.api.Test;
import org.karane.mapstruct.dto.PersonDisplayDTO;
import org.karane.mapstruct.model.Address;
import org.karane.mapstruct.model.Person;
import org.karane.mapstruct.model.PhoneNumber;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Ex05ExpressionMapperTest {

    private final Ex05ExpressionMapper mapper = Ex05ExpressionMapper.INSTANCE;

    private Person person() {
        return new Person(1L, "Jane", "Smith", "jane@example.com",
                LocalDate.of(1985, 3, 20),
                new Address("1 St", "City", "ST", "00001"),
                new PhoneNumber("+1-000-000-0000"));
    }

    @Test
    void toDTO_concatenatesFullName() {
        PersonDisplayDTO dto = mapper.toDTO(person());
        assertEquals("Jane Smith", dto.getFullName());
    }

    @Test
    void toDTO_extractsBirthYear() {
        PersonDisplayDTO dto = mapper.toDTO(person());
        assertEquals(1985, dto.getBirthYear());
    }
}
