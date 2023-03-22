package edu.miu.service.impl;

import edu.miu.dtos.CommentRequestDto;
import edu.miu.dtos.CommentResponseDto;
import edu.miu.entity.Comment;
import edu.miu.exception.CommentNotFoundException;
import edu.miu.repository.CommentRepository;
import edu.miu.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper mapper;

    @Override
    public CommentRequestDto save(CommentRequestDto commentDto) {
        Comment comment = mapper.map(commentDto, Comment.class);
        comment.setCreatedDate(LocalDate.now());
        return mapper.map(commentRepository.save(comment), CommentRequestDto.class);

    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public CommentResponseDto getById(Long id) throws CommentNotFoundException {
        Comment comment = commentRepository.findCommentById(id).orElseThrow(
                () -> new CommentNotFoundException("comment with id " + id + " not found"));
        return mapper.map(comment, CommentResponseDto.class);
    }

    @Override
    public List<CommentResponseDto> getAll() {
        Iterable<Comment> comments = commentRepository.findAll();
        List<CommentResponseDto> result = StreamSupport.stream(comments.spliterator(), false)
                .map(a -> mapper.map(a, CommentResponseDto.class))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<CommentResponseDto> getAllByMovieId(Long movieId) throws CommentNotFoundException {
        Iterable<Comment> comments = commentRepository.findCommentByMovieId(movieId).orElseThrow(
                () -> new CommentNotFoundException("comment with movie id " + movieId + " not found"));
        List<CommentResponseDto> result = StreamSupport.stream(comments.spliterator(), false)
                .map(a -> mapper.map(a, CommentResponseDto.class))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<CommentResponseDto> getAllByUserId(Long userId) throws CommentNotFoundException {
        Iterable<Comment> comments = commentRepository.findCommentByUserId(userId).orElseThrow(
                () -> new CommentNotFoundException("comment with user id " + userId + " not found"));
        List<CommentResponseDto> result = StreamSupport.stream(comments.spliterator(), false)
                .map(a -> mapper.map(a, CommentResponseDto.class))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<CommentResponseDto> getAllByUserIdAndMovieId(Long userId, Long movieId) throws CommentNotFoundException {
        Iterable<Comment> comments = commentRepository.findCommentByMovieIdAndUserId(movieId, userId).orElseThrow(
                () -> new CommentNotFoundException("comment with movie id " + movieId + " and user id" + userId + " not found"));
        List<CommentResponseDto> result = StreamSupport.stream(comments.spliterator(), false)
                .map(a -> mapper.map(a, CommentResponseDto.class))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public void update(Long id, CommentRequestDto commentDto) throws CommentNotFoundException {
        var existingEntity = commentRepository.findCommentById(id).orElseThrow(
                () -> new CommentNotFoundException("comment with id " + id + " not found"));
        existingEntity.setTitle(commentDto.getTitle());
        existingEntity.setDescription(commentDto.getDescription());
        existingEntity.setUpdatedDate(LocalDate.now());
        commentRepository.save(existingEntity);
    }
}
