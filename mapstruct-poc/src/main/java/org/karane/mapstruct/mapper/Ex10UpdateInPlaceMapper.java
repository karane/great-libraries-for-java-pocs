package org.karane.mapstruct.mapper;

import org.karane.mapstruct.dto.PersonUpdateDTO;
import org.karane.mapstruct.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * Ex10 - In-place update with @MappingTarget.
 * Instead of creating a new object, MapStruct updates an existing instance.
 * Only email and address are writable; all other Person fields are ignored.
 * uses = Ex03ReverseMappingMapper to convert AddressDTO -> Address when updating address.
 */
@Mapper(uses = Ex03ReverseMappingMapper.class)
public interface Ex10UpdateInPlaceMapper {

    Ex10UpdateInPlaceMapper INSTANCE = Mappers.getMapper(Ex10UpdateInPlaceMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "firstName", ignore = true)
    @Mapping(target = "lastName", ignore = true)
    @Mapping(target = "birthDate", ignore = true)
    @Mapping(target = "phoneNumber", ignore = true)
    void update(PersonUpdateDTO dto, @MappingTarget Person person);
}
