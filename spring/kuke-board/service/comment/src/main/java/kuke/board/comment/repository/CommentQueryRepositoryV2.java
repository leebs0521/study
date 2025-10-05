package kuke.board.comment.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kuke.board.comment.entity.CommentV2;
import kuke.board.comment.entity.QCommentV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CommentQueryRepositoryV2 {

    private final JPAQueryFactory queryFactory;

    private static final QCommentV2 comment = QCommentV2.commentV2;

    public Optional<CommentV2> findByPath(String path) {
        return Optional.ofNullable(
                queryFactory.selectFrom(comment)
                        .where(comment.commentPath.path.eq(path))
                        .fetchOne());
    }

    public Optional<String> findDescendantsTopPath(
            Long articleId, String pathPrefix
    ) {
        return Optional.ofNullable(
                queryFactory.select(comment.commentPath.path)
                        .from(comment)
                        .where(comment.articleId.eq(articleId),
                                comment.commentPath.path.gt(pathPrefix),
                                comment.commentPath.path.startsWith(pathPrefix))
                        .orderBy(comment.commentPath.path.desc())
                        .limit(1)
                        .fetchOne()
        );
    }

    public List<CommentV2> findAll(Long articleId, Long offset, Long limit) {
        List<Long> ids = queryFactory
                .select(comment.commentId)
                .from(comment)
                .where(comment.articleId.eq(articleId))
                .orderBy(comment.commentPath.path.asc())
                .limit(limit)
                .offset(offset)
                .fetch();

        return queryFactory
                .selectFrom(comment)
                .where(comment.commentId.in(ids))
                .orderBy(comment.commentPath.path.asc())
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

    public List<CommentV2> findAllInfiniteScroll(Long articleId, Long limit) {
        return queryFactory
                .selectFrom(comment)
                .where(comment.articleId.eq(articleId))
                .orderBy(comment.commentPath.path.asc())
                .limit(limit)
                .fetch();
    }

    public List<CommentV2> findAllInfiniteScroll(Long articleId, String lastPath, Long limit) {
        return queryFactory
                .selectFrom(comment)
                .where(
                        comment.articleId.eq(articleId),
                        comment.commentPath.path.goe(lastPath))
                .orderBy(comment.commentPath.path.asc())
                .limit(limit)
                .fetch();
    }
}
