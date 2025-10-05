package kuke.board.comment.repository;

import kuke.board.comment.entity.CommentV2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepositoryV2 extends JpaRepository<CommentV2, Long> {

}
