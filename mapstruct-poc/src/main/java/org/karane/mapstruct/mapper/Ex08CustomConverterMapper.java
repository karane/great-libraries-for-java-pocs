package org.karane.mapstruct.mapper;

import org.karane.mapstruct.dto.PersonContactDTO;
import org.karane.mapstruct.model.Person;
import org.karane.mapstruct.model.PhoneNumber;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

/**
 * Ex08 - Custom converter.
 * @Named marks a default method as a named conversion routine.
 * qualifiedByName = "..." selects it explicitly, allowing full control
 * over the conversion logic (null-safety, formatting, parsing, etc.).
 *
 * Ex04 uses dot notation (source = "phoneNumber.value"). 
 * Ex08 uses a @Named method which is arbitrary Java code.
 */
@Mapper
public interface Ex08CustomConverterMapper {

    Ex08CustomConverterMapper INSTANCE = Mappers.getMapper(Ex08CustomConverterMapper.class);

    @Mapping(target = "phoneNumber", source = "phoneNumber", qualifiedByName = "phoneNumberToString")
    PersonContactDTO toDTO(Person person);

    @Named("phoneNumberToString")
    default String phoneNumberToString(PhoneNumber phoneNumber) {
        return phoneNumber != null ? phoneNumber.getValue() : null;
    }
}
