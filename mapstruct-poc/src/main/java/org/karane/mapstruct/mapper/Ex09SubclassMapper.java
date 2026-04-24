package org.karane.mapstruct.mapper;

import org.karane.mapstruct.dto.EmployeeBaseDTO;
import org.karane.mapstruct.dto.PersonSameNameDTO;
import org.karane.mapstruct.model.Employee;
import org.karane.mapstruct.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.SubclassMapping;
import org.mapstruct.factory.Mappers;

/**
 * Ex09 - Subclass mapping (inheritance dispatch).
 * @SubclassMapping declares that when the runtime type of the source is Employee,
 * MapStruct delegates to toDTO(Employee) instead of the generic toDTO(Person).
 * The caller always works with the Person supertype; the dispatch is transparent.
 */
@Mapper
public interface Ex09SubclassMapper {

    Ex09SubclassMapper INSTANCE = Mappers.getMapper(Ex09SubclassMapper.class);

    @SubclassMapping(source = Employee.class, target = EmployeeBaseDTO.class)
    PersonSameNameDTO toDTO(Person person);

    EmployeeBaseDTO toDTO(Employee employee);
}
