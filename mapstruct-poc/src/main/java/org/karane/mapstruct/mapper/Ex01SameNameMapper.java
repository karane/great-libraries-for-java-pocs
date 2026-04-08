package org.karane.mapstruct.mapper;

import org.karane.mapstruct.dto.PersonSameNameDTO;
import org.karane.mapstruct.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Ex01 - Same-name field mapping.
 * MapStruct automatically maps fields with identical names and compatible types.
 * No @Mapping annotations are needed.
 */
@Mapper
public interface Ex01SameNameMapper {

    Ex01SameNameMapper INSTANCE = Mappers.getMapper(Ex01SameNameMapper.class);

    PersonSameNameDTO toDTO(Person person);
}
