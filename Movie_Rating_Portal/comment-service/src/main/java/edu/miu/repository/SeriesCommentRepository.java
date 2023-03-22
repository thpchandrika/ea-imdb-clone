package edu.miu.repository;

import edu.miu.entity.SeriesComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeriesCommentRepository extends JpaRepository<SeriesComment,Long> {
    Optional<SeriesComment> findCommentById(Long id);
    Optional<List<SeriesComment>> findCommentByUserId(Long userId);
    Optional<List<SeriesComment>> findCommentBySeriesId(Long seriesId);
    Optional<List<SeriesComment>> findCommentBySeriesIdAndUserId(Long seriesId, Long userId);
}
