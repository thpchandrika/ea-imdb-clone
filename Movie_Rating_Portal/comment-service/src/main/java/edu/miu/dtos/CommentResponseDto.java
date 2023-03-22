package edu.miu.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
@ApiModel(description = "Response model for comment")
public class CommentResponseDto {
    private Long id;

    private String title;

    private String description;

    @ApiModelProperty(notes = "movie details")
    private MovieDto movie;

    @ApiModelProperty(notes = "user details")
    private UserDto user;

    private LocalDate createdDate;

    private LocalDate updatedDate;
}
