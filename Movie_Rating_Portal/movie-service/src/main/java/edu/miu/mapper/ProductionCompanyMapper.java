package edu.miu.mapper;

import edu.miu.dto.ProductionCompanyDto;
import edu.miu.entity.ProductionCompany;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductionCompanyMapper {
    ProductionCompanyDto toProductionCompanyDto(ProductionCompany productionCompany);
}
