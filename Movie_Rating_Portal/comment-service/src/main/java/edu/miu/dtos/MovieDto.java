package edu.miu.dtos;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Movie model on which user comment")
public class MovieDto {
    private String title;
    private String description;
    private String genre;
    private String releaseYear;
    private String runTime;
}
