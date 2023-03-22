package edu.miu.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
@ApiModel(description = "Response model for comment")
public class SeriesCommentResponseDto {
    private Long id;

    private String title;

    private String description;

    @ApiModelProperty(notes = "movie details")
    private SeriesDto series;

    @ApiModelProperty(notes = "user details")
    private UserDto user;

    private LocalDate createdDate;

    private LocalDate updatedDate;
}
