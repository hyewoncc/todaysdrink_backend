package com.todaysdrink.todaysdrink.service;

import com.todaysdrink.todaysdrink.domain.Beer;
import com.todaysdrink.todaysdrink.domain.Comment;
import com.todaysdrink.todaysdrink.domain.LikeComment;
import com.todaysdrink.todaysdrink.dto.CommentDto;
import com.todaysdrink.todaysdrink.repository.BeerRepository;
import com.todaysdrink.todaysdrink.repository.CommentRepository;
import com.todaysdrink.todaysdrink.repository.LikeCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final BeerRepository beerRepository;
    private final CommentRepository commentRepository;
    private final LikeCommentRepository likeCommentRepository;


    // 댓글 추가
    @Transactional
    public Comment addComment(CommentDto commentDto) {
        Beer beer = beerRepository.getById(commentDto.getBeerId());
        Comment comment = Comment.createComment(beer, commentDto);
        commentRepository.save(comment);
        return comment;
    }


    // 댓글 좋아요 추가
    @Transactional
    public LikeComment addLikeComment(Comment comment) {
        LikeComment likeComment = LikeComment.createLikeComment(comment);
        likeCommentRepository.save(likeComment);
        return likeComment;
    }


    // 특정 맥주의 댓글 목록 반환
    public List<CommentDto> getCommentList(Long beerId, Pageable pageable) {
        Beer beer = beerRepository.getById(beerId);
        Page<Comment> comments = commentRepository.findAllByBeer(beer, pageable);
        List<CommentDto> result = comments.stream()
                .map(c -> new CommentDto(c))
                .collect(Collectors.toList());
        return result;
    }


    // 댓글 좋아요 실행 후 조회
    @Transactional
    public Long upLikeComment(Long likeCommentId) {
        LikeComment likeComment = likeCommentRepository.getById(likeCommentId);
        likeComment.upCount();
        likeCommentRepository.save(likeComment);
        return likeComment.getCount();
    }


    // 댓글 좋아요 취소 후 조회
    @Transactional
    public Long downLikeComment(Long likeCommentId) {
        LikeComment likeComment = likeCommentRepository.getById(likeCommentId);
        likeComment.downCount();
        likeCommentRepository.save(likeComment);
        return likeComment.getCount();
    }
}
