package kuke.board.comment.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kuke.board.comment.entity.Comment;
import kuke.board.comment.entity.QComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class CommentQueryRepository {

    private final JPAQueryFactory queryFactory;

    private static final QComment comment = QComment.comment;

    public Long countBy(Long articleId, Long parentCommentId, Long limit) {
        Long totalCount = queryFactory
                .select(comment.commentId.count())
                .from(comment)
                .where(
                        comment.articleId.eq(articleId),
                        comment.parentCommentId.eq(parentCommentId)
                )
                .fetchOne();

        return totalCount == null ? 0L : Math.min(totalCount, limit);
    }

    public List<Comment> findAll(Long articleId, Long offset, Long limit) {
        List<Long> ids = queryFactory
                .select(comment.commentId)
                .from(comment)
                .where(
                        comment.articleId.eq(articleId)
                )
                .orderBy(comment.parentCommentId.asc(), comment.commentId.asc())
                .limit(limit)
                .offset(offset)
                .fetch();

        return queryFactory
                .selectFrom(comment)
                .where(comment.commentId.in(ids))
                .fetch();
    }

    public Long count(Long articleId, Long limit) {
        Long totalCount = queryFactory
                .select(comment.commentId.count())
                .from(comment)
                .where(comment.articleId.eq(articleId))
                .fetchOne();

        return totalCount == null ? 0L : Math.min(totalCount, limit);
    }

    public List<Comment> findAllInfiniteScroll(Long articleId, Long limit) {
        return queryFactory
                .selectFrom(comment)
                .where(comment.articleId.eq(articleId))
                .orderBy(comment.parentCommentId.asc(), comment.commentId.asc())
                .limit(limit)
                .fetch();
    }

    public List<Comment> findAllInfiniteScroll(Long articleId, Long lastParentCommentId, Long lastCommentId, Long limit) {
        BooleanExpression cursorCondition = comment.parentCommentId.gt(lastParentCommentId)
                .or(comment.parentCommentId.eq(lastParentCommentId)
                        .and(comment.commentId.gt(lastCommentId)));
        return queryFactory
                .selectFrom(comment)
                .where(
                        comment.articleId.eq(articleId),
                        cursorCondition
                )
                .orderBy(comment.parentCommentId.asc(), comment.commentId.asc())
                .limit(limit)
                .fetch();
    }

}
