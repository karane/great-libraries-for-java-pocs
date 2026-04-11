package org.karane.mapstruct.mapper;

import org.karane.mapstruct.dto.PersonContactDTO;
import org.karane.mapstruct.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Ex04 - Nested property access.
 * Using dot notation in source navigates into a nested object's field directly.
 * "phoneNumber.value" reaches into the PhoneNumber value object to extract the String.
 */
@Mapper
public interface Ex04NestedPropertyMapper {

    Ex04NestedPropertyMapper INSTANCE = Mappers.getMapper(Ex04NestedPropertyMapper.class);

    @Mapping(source = "phoneNumber.value", target = "phoneNumber")
    PersonContactDTO toDTO(Person person);
}
