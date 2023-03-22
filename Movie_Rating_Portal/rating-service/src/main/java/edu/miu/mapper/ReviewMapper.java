package edu.miu.mapper;

import edu.miu.dto.ReviewDto;
import edu.miu.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReviewMapper {

    Review dtoToEntity(ReviewDto reviewDto);

    Review entityToDto(Review review);

    List<ReviewDto> entitiesToDtos(List<Review> reviews);
}
