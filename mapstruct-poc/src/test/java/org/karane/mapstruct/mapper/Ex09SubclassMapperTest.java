package org.karane.mapstruct.mapper;

import org.junit.jupiter.api.Test;
import org.karane.mapstruct.dto.EmployeeBaseDTO;
import org.karane.mapstruct.dto.PersonSameNameDTO;
import org.karane.mapstruct.model.Address;
import org.karane.mapstruct.model.Employee;
import org.karane.mapstruct.model.Person;
import org.karane.mapstruct.model.PhoneNumber;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Ex09SubclassMapperTest {

    private final Ex09SubclassMapper mapper = Ex09SubclassMapper.INSTANCE;

    private Person person() {
        return new Person(1L, "Jane", "Smith", "jane@example.com",
                LocalDate.of(1985, 1, 1),
                new Address("1 St", "City", "ST", "00001"),
                new PhoneNumber("+1-000-000-0000"));
    }

    private Employee employee() {
        return new Employee(2L, "Bob", "Jones", "bob@example.com",
                LocalDate.of(1990, 1, 1),
                new Address("2 Ave", "Town", "TX", "00002"),
                new PhoneNumber("+1-111-111-1111"),
                "Engineering");
    }

    @Test
    void toDTO_withPerson_returnsPersonSameNameDTO() {
        PersonSameNameDTO dto = mapper.toDTO(person());
        assertFalse(dto instanceof EmployeeBaseDTO);
        assertEquals(1L, dto.getId());
    }

    @Test
    void toDTO_withEmployee_returnsEmployeeBaseDTO() {
        PersonSameNameDTO dto = mapper.toDTO(employee());
        assertInstanceOf(EmployeeBaseDTO.class, dto);
    }

    @Test
    void toDTO_withEmployee_mapsDepartment() {
        EmployeeBaseDTO dto = (EmployeeBaseDTO) mapper.toDTO(employee());
        assertEquals("Engineering", dto.getDepartment());
        assertEquals(2L, dto.getId());
    }

    @Test
    void toDTO_polymorphicDispatchInList() {
        List<PersonSameNameDTO> dtos = List.of(person(), employee()).stream()
                .map(mapper::toDTO).toList();
        assertFalse(dtos.get(0) instanceof EmployeeBaseDTO);
        assertInstanceOf(EmployeeBaseDTO.class, dtos.get(1));
    }
}
