package org.karane.mapstruct.mapper;

import org.karane.mapstruct.dto.AddressDTO;
import org.karane.mapstruct.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Ex02 - Field rename.
 * @Mapping(source, target) explicitly maps a source field to a differently named target field.
 * All other same-name fields (street, city, state) are still mapped automatically.
 */
@Mapper
public interface Ex02FieldRenameMapper {

    Ex02FieldRenameMapper INSTANCE = Mappers.getMapper(Ex02FieldRenameMapper.class);

    @Mapping(source = "zipCode", target = "postalCode")
    AddressDTO toDTO(Address address);
}
