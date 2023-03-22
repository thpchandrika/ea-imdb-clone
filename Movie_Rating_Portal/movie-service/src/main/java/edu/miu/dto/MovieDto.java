package edu.miu.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Data
@Getter
@Setter
@NoArgsConstructor
public class MovieDto implements Serializable {
    private String title;
    private String description;
    private String genre;
    private String releaseYear;

    private String runTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public ProductionCompanyDto getProductionCompanyDto() {
        return productionCompanyDto;
    }

    public void setProductionCompanyDto(ProductionCompanyDto productionCompanyDto) {
        this.productionCompanyDto = productionCompanyDto;
    }

    private ProductionCompanyDto productionCompanyDto;
}
