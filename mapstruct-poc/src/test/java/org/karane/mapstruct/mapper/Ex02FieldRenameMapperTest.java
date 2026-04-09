package org.karane.mapstruct.mapper;

import org.junit.jupiter.api.Test;
import org.karane.mapstruct.dto.AddressDTO;
import org.karane.mapstruct.model.Address;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Ex02FieldRenameMapperTest {

    private final Ex02FieldRenameMapper mapper = Ex02FieldRenameMapper.INSTANCE;

    private Address address() {
        return new Address("10 Elm St", "Chicago", "IL", "60601");
    }

    @Test
    void toDTO_renamesZipCodeToPostalCode() {
        AddressDTO dto = mapper.toDTO(address());
        assertEquals("60601", dto.getPostalCode());
    }

    @Test
    void toDTO_stillMapsSameNameFields() {
        AddressDTO dto = mapper.toDTO(address());
        assertEquals("10 Elm St", dto.getStreet());
        assertEquals("Chicago", dto.getCity());
        assertEquals("IL", dto.getState());
    }
}
