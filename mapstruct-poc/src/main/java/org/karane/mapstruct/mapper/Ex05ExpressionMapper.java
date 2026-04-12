package org.karane.mapstruct.mapper;

import org.karane.mapstruct.dto.PersonDisplayDTO;
import org.karane.mapstruct.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Ex05 - Expression mapping.
 * expression = "java(...)" embeds inline Java code to derive a target field
 * from one or more source fields that have no direct name match.
 */
@Mapper
public interface Ex05ExpressionMapper {

    Ex05ExpressionMapper INSTANCE = Mappers.getMapper(Ex05ExpressionMapper.class);

    @Mapping(target = "fullName", expression = "java(person.getFirstName() + \" \" + person.getLastName())")
    @Mapping(target = "birthYear", expression = "java(person.getBirthDate().getYear())")
    PersonDisplayDTO toDTO(Person person);
}
