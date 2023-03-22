package edu.miu.service.impl;

import edu.miu.dtos.SeriesCommentRequestDto;
import edu.miu.dtos.SeriesCommentResponseDto;
import edu.miu.entity.SeriesComment;
import edu.miu.exception.CommentNotFoundException;
import edu.miu.repository.SeriesCommentRepository;
import edu.miu.service.SeriesCommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class SeriesCommentServiceImpl implements SeriesCommentService {
    private final SeriesCommentRepository commentRepository;
    private final ModelMapper mapper;

    @Override
    public SeriesCommentRequestDto save(SeriesCommentRequestDto commentDto) {
        SeriesComment comment = mapper.map(commentDto, SeriesComment.class);
        comment.setCreatedDate(LocalDate.now());
        SeriesComment savedComment = commentRepository.save(comment);
        return mapper.map(savedComment, SeriesCommentRequestDto.class);
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public SeriesCommentResponseDto getById(Long id) throws CommentNotFoundException {
        SeriesComment comment = commentRepository.findCommentById(id).orElseThrow(() ->
                new CommentNotFoundException("comment with id " + id + " not found"));
        return mapper.map(comment, SeriesCommentResponseDto.class);
    }

    @Override
    public List<SeriesCommentResponseDto> getAll() {
        Iterable<SeriesComment> comments = commentRepository.findAll();
        List<SeriesCommentResponseDto> result = StreamSupport.stream(comments.spliterator(), false)
                .map(a -> mapper.map(a, SeriesCommentResponseDto.class))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<SeriesCommentResponseDto> getAllByUserId(Long userId) throws CommentNotFoundException {
        Iterable<SeriesComment> comments = commentRepository.findCommentByUserId(userId).orElseThrow(() ->
                new CommentNotFoundException("comment with user id " + userId + " not found"));
        List<SeriesCommentResponseDto> result = StreamSupport.stream(comments.spliterator(), false)
                .map(a -> mapper.map(a, SeriesCommentResponseDto.class))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<SeriesCommentResponseDto> getAllBySeriesId(Long seriesId) throws CommentNotFoundException {
        Iterable<SeriesComment> comments = commentRepository.findCommentBySeriesId(seriesId).orElseThrow(() ->
                new CommentNotFoundException("comment with series id " + seriesId + " not found"));
        List<SeriesCommentResponseDto> result = StreamSupport.stream(comments.spliterator(), false)
                .map(a -> mapper.map(a, SeriesCommentResponseDto.class))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<SeriesCommentResponseDto> getAllBySeriesIdAndUserId(Long seriesId, Long userId)
            throws CommentNotFoundException {
        Iterable<SeriesComment> comments = commentRepository.findCommentBySeriesIdAndUserId(seriesId, userId)
                .orElseThrow(() -> new CommentNotFoundException("comment with series id " + seriesId
                        + " and user id" + userId + " not found"));
        List<SeriesCommentResponseDto> result = StreamSupport.stream(comments.spliterator(), false)
                .map(a -> mapper.map(a, SeriesCommentResponseDto.class))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public void update(Long id, SeriesCommentRequestDto commentDto) throws CommentNotFoundException {
        var existingEntity = commentRepository.findCommentById(id).orElseThrow(() ->
                new CommentNotFoundException("comment with id " + id + " not found"));
        existingEntity.setTitle(commentDto.getTitle());
        existingEntity.setDescription(commentDto.getDescription());
        existingEntity.setUpdatedDate(LocalDate.now());
        commentRepository.save(existingEntity);
    }
}
