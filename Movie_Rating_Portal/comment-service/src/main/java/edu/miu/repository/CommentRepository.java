package edu.miu.repository;

import edu.miu.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findCommentById(Long id);
    Optional<List<Comment>> findCommentByUserId(Long userId);
    Optional<List<Comment>> findCommentByMovieId(Long movieId);
    Optional<List<Comment>> findCommentByMovieIdAndUserId(Long movieId, Long userId);
}
