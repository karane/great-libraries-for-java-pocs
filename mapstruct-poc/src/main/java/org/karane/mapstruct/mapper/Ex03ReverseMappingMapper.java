package org.karane.mapstruct.mapper;

import org.karane.mapstruct.dto.AddressDTO;
import org.karane.mapstruct.model.Address;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Ex03 - Reverse mapping.
 * @InheritInverseConfiguration reuses the forward mapping's @Mapping annotations
 * in reverse, so postalCode -> zipCode is derived automatically from Ex02's toDTO.
 */
@Mapper
public interface Ex03ReverseMappingMapper {

    Ex03ReverseMappingMapper INSTANCE = Mappers.getMapper(Ex03ReverseMappingMapper.class);

    @Mapping(source = "zipCode", target = "postalCode")
    AddressDTO toDTO(Address address);

    @InheritInverseConfiguration
    Address toModel(AddressDTO dto);
}
