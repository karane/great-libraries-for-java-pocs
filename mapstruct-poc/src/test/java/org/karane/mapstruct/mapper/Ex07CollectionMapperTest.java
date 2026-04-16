package org.karane.mapstruct.mapper;

import org.junit.jupiter.api.Test;
import org.karane.mapstruct.dto.PersonDisplayDTO;
import org.karane.mapstruct.model.Address;
import org.karane.mapstruct.model.Person;
import org.karane.mapstruct.model.PhoneNumber;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Ex07CollectionMapperTest {

    private final Ex07CollectionMapper mapper = Ex07CollectionMapper.INSTANCE;

    private Person person(String first, String last, int year) {
        return new Person(1L, first, last, "x@x.com",
                LocalDate.of(year, 1, 1),
                new Address("1 St", "City", "ST", "00001"),
                new PhoneNumber("+1-000-000-0000"));
    }

    @Test
    void toDTOList_mapsAllElements() {
        List<PersonDisplayDTO> dtos = mapper.toDTOList(
                List.of(person("Alice", "A", 1990), person("Bob", "B", 1995), person("Carol", "C", 2000)));
        assertEquals(3, dtos.size());
    }

    @Test
    void toDTOList_appliesExpressionMappingPerElement() {
        List<PersonDisplayDTO> dtos = mapper.toDTOList(
                List.of(person("Alice", "Liddell", 1990), person("Bob", "Builder", 1995)));
        assertEquals("Alice Liddell", dtos.get(0).getFullName());
        assertEquals(1990, dtos.get(0).getBirthYear());
        assertEquals("Bob Builder", dtos.get(1).getFullName());
        assertEquals(1995, dtos.get(1).getBirthYear());
    }
}
