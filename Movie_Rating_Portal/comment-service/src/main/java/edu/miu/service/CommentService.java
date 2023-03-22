package edu.miu.service;

import edu.miu.dtos.CommentRequestDto;
import edu.miu.dtos.CommentResponseDto;
import edu.miu.exception.CommentNotFoundException;

import java.util.List;

public interface CommentService {
    CommentRequestDto save(CommentRequestDto commentDto);

    void delete(Long id);

    CommentResponseDto getById(Long id) throws CommentNotFoundException;

    List<CommentResponseDto> getAll();

    List<CommentResponseDto> getAllByMovieId(Long movieId) throws CommentNotFoundException;
    List<CommentResponseDto> getAllByUserId(Long userId) throws CommentNotFoundException;
    List<CommentResponseDto> getAllByUserIdAndMovieId(Long userId, Long movieId) throws CommentNotFoundException;

    void update(Long id, CommentRequestDto commentDto) throws CommentNotFoundException;
}
