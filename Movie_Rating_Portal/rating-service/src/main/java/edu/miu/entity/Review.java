package edu.miu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double rate;
    private int likeButton;
    private int dislikeButton;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private LocalDate deletedDate;
    private String statusName;
    private Long userId;
    private Long movieId;
    private Long tvSeriesId;
    private boolean isUserDeleted;
}
