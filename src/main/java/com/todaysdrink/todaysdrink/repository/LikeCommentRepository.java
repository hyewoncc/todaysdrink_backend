package com.todaysdrink.todaysdrink.repository;

import com.todaysdrink.todaysdrink.domain.LikeComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeCommentRepository extends JpaRepository<LikeComment, Long> {
}
