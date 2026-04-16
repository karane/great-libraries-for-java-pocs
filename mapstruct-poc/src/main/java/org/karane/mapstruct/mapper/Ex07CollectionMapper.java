package org.karane.mapstruct.mapper;

import org.karane.mapstruct.dto.PersonDisplayDTO;
import org.karane.mapstruct.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Ex07 - Collection mapping.
 * MapStruct generates collection mapping by locating an element-level method
 * in the referenced mapper (Ex05ExpressionMapper.toDTO(Person)).
 * No extra annotations are needed on the list method.
 */
@Mapper(uses = Ex05ExpressionMapper.class)
public interface Ex07CollectionMapper {

    Ex07CollectionMapper INSTANCE = Mappers.getMapper(Ex07CollectionMapper.class);

    List<PersonDisplayDTO> toDTOList(List<Person> persons);
}
