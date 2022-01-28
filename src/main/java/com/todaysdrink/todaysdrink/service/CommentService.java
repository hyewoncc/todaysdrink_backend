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
import java.util.Optional;
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
    public Comment saveComment(CommentDto commentDto) {
        Beer beer = beerRepository.getById(commentDto.getBeerId());
        LikeComment likeComment = this.addLikeComment();
        Comment comment = Comment.createComment(commentDto, likeComment, beer);
        commentRepository.save(comment);
        return comment;
    }


    // 댓글 좋아요 추가
    @Transactional
    public LikeComment addLikeComment() {
        LikeComment likeComment = LikeComment.createLikeComment();
        likeCommentRepository.save(likeComment);
        return likeComment;
    }


    // 특정 맥주의 댓글 목록 반환
    public List<Comment> getCommentList(Long beerId) {
        Beer beer = beerRepository.getById(beerId);
        List<Comment> result = commentRepository.findAllByBeer(beer);
        return result;
    }
    
    
    // 댓글 좋아요 조회
    public Optional<LikeComment> getLikeComment(Long likeCommentId) {
        Optional<LikeComment> likeComment = likeCommentRepository.findById(likeCommentId);
        return likeComment;
    }


    // 댓글 좋아요 실행 후 조회
    @Transactional
    public LikeComment upLikeComment(Long likeCommentId) {
        LikeComment likeComment = likeCommentRepository.getById(likeCommentId);
        likeComment.upCount();
        likeCommentRepository.save(likeComment);
        return likeComment;
    }


    // 댓글 좋아요 취소 후 조회
    @Transactional
    public LikeComment downLikeComment(Long likeCommentId) {
        LikeComment likeComment = likeCommentRepository.getById(likeCommentId);
        likeComment.downCount();
        likeCommentRepository.save(likeComment);
        return likeComment;
    }
}
