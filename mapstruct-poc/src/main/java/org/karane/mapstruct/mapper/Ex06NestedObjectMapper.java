package org.karane.mapstruct.mapper;

import org.karane.mapstruct.dto.PersonWithAddressDTO;
import org.karane.mapstruct.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Ex06 - Nested object mapping.
 * uses = {...} delegates the conversion of nested types to another mapper.
 * MapStruct finds Ex02FieldRenameMapper.toDTO(Address) and calls it automatically
 * when it needs to map Person.address (Address) to PersonWithAddressDTO.address (AddressDTO).
 */
@Mapper(uses = Ex02FieldRenameMapper.class)
public interface Ex06NestedObjectMapper {

    Ex06NestedObjectMapper INSTANCE = Mappers.getMapper(Ex06NestedObjectMapper.class);

    PersonWithAddressDTO toDTO(Person person);
}
