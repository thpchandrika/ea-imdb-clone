package edu.miu.service;

import edu.miu.dtos.SeriesCommentRequestDto;
import edu.miu.dtos.SeriesCommentResponseDto;
import edu.miu.exception.CommentNotFoundException;

import java.util.List;

public interface SeriesCommentService {
    SeriesCommentRequestDto save(SeriesCommentRequestDto commentDto);

    void delete(Long id);

    SeriesCommentResponseDto getById(Long id) throws CommentNotFoundException;

    List<SeriesCommentResponseDto> getAll();

    List<SeriesCommentResponseDto> getAllByUserId(Long userId) throws CommentNotFoundException;
    List<SeriesCommentResponseDto> getAllBySeriesId(Long seriesId) throws CommentNotFoundException;
    List<SeriesCommentResponseDto> getAllBySeriesIdAndUserId(Long seriesId, Long userId) throws CommentNotFoundException;

    void update(Long id, SeriesCommentRequestDto commentDto) throws CommentNotFoundException;
}
