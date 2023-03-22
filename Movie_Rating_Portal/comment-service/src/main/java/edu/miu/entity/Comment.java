package edu.miu.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "moviescomments")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Long movieId;

    private Long userId;

    private LocalDate createdDate;

    private LocalDate updatedDate;
}
