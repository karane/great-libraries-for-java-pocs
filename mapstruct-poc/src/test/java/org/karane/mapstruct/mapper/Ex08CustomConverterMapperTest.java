package org.karane.mapstruct.mapper;

import org.junit.jupiter.api.Test;
import org.karane.mapstruct.dto.PersonContactDTO;
import org.karane.mapstruct.model.Address;
import org.karane.mapstruct.model.Person;
import org.karane.mapstruct.model.PhoneNumber;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class Ex08CustomConverterMapperTest {

    private final Ex08CustomConverterMapper mapper = Ex08CustomConverterMapper.INSTANCE;

    private Person person(PhoneNumber phone) {
        return new Person(1L, "Jane", "Doe", "jane@example.com",
                LocalDate.of(1990, 1, 1),
                new Address("1 St", "City", "ST", "00001"),
                phone);
    }

    @Test
    void toDTO_convertsPhoneNumberObjectToString() {
        PersonContactDTO dto = mapper.toDTO(person(new PhoneNumber("+1-312-555-0200")));
        assertEquals("+1-312-555-0200", dto.getPhoneNumber());
    }

    @Test
    void toDTO_nullPhoneNumber_mapsToNull() {
        PersonContactDTO dto = mapper.toDTO(person(null));
        assertNull(dto.getPhoneNumber());
    }
}
