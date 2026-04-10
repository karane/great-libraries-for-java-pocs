package org.karane.mapstruct.mapper;

import org.junit.jupiter.api.Test;
import org.karane.mapstruct.dto.AddressDTO;
import org.karane.mapstruct.model.Address;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Ex03ReverseMappingMapperTest {

    private final Ex03ReverseMappingMapper mapper = Ex03ReverseMappingMapper.INSTANCE;

    private Address address() {
        return new Address("99 Oak Ave", "Austin", "TX", "78701");
    }

    @Test
    void toDTO_renamesZipCodeToPostalCode() {
        AddressDTO dto = mapper.toDTO(address());
        assertEquals("78701", dto.getPostalCode());
    }

    @Test
    void toModel_reverseRestoresZipCode() {
        AddressDTO dto = mapper.toDTO(address());
        Address restored = mapper.toModel(dto);
        assertEquals("78701", restored.getZipCode());
    }

    @Test
    void toModel_reverseRestoresSameNameFields() {
        AddressDTO dto = mapper.toDTO(address());
        Address restored = mapper.toModel(dto);
        assertEquals("99 Oak Ave", restored.getStreet());
        assertEquals("Austin", restored.getCity());
        assertEquals("TX", restored.getState());
    }
}
