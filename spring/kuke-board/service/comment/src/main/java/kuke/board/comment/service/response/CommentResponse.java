package kuke.board.comment.service.response;

import kuke.board.comment.entity.Comment;
import kuke.board.comment.entity.CommentV2;

import java.time.LocalDateTime;

public record CommentResponse(
        Long commentId,
        String content,
        Long parentCommentId,
        Long articleId,
        Long writerId,
        Boolean deleted,
        String path,
        LocalDateTime createdAt
) {

    public static CommentResponse from(Comment comment) {
        return new CommentResponse(
                comment.getCommentId(),
                comment.getContent(),
                comment.getParentCommentId(),
                comment.getArticleId(),
                comment.getWriterId(),
                comment.getDeleted(),
                null,
                comment.getCreatedAt()
        );
    }

    public static CommentResponse from(CommentV2 comment) {
        return new CommentResponse(
                comment.getCommentId(),
                comment.getContent(),
                comment.getCommentId(),
                comment.getArticleId(),
                comment.getWriterId(),
                comment.getDeleted(),
                comment.getCommentPath().getPath(),
                comment.getCreatedAt()
        );

    }
}
