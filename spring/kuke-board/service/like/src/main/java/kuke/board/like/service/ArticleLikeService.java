package kuke.board.like.service;

import kuke.board.common.snowflake.Snowflake;
import kuke.board.like.entity.ArticleLike;
import kuke.board.like.entity.ArticleLikeCount;
import kuke.board.like.repository.ArticleLikeCountRepository;
import kuke.board.like.repository.ArticleLikeRepository;
import kuke.board.like.service.response.ArticleLikeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleLikeService {

    private final ArticleLikeRepository likeRepository;
    private final ArticleLikeCountRepository likeCountRepository;
    private final Snowflake snowflake = new Snowflake();

    @Transactional(readOnly = true)
    public ArticleLikeResponse read(Long articleId, Long userId) {
        return likeRepository.findByArticleIdAndUserId(articleId, userId)
                .map(ArticleLikeResponse::from)
                .orElseThrow();
    }

    public void likePessimisticLock1(Long articleId, Long userId) {
        ArticleLike articleLike = likeRepository.save(
                ArticleLike.create(
                        snowflake.nextId(),
                        articleId,
                        userId
                )
        );

        int res = likeCountRepository.increase(articleId);
        if (res == 0) {
            likeCountRepository.save(ArticleLikeCount.init(articleId, 1L));
        }
    }

    public void unlikePessimisticLock1(Long articleId, Long userId) {
        likeRepository.findByArticleIdAndUserId(articleId, userId)
                .ifPresent(articleLike -> {
                    likeRepository.delete(articleLike);
                    likeCountRepository.decrease(articleId);
                });
    }

    public void likePessimisticLock2(Long articleId, Long userId) {
        likeRepository.save(
                ArticleLike.create(
                        snowflake.nextId(),
                        articleId,
                        userId
                )
        );

        ArticleLikeCount likeCount = likeCountRepository.findLockedByArticleId(articleId)
                .orElseGet(() -> likeCountRepository.save(ArticleLikeCount.init(articleId, 0L)));

        likeCount.increase();
    }

    public void unlikePessimisticLock2(Long articleId, Long userId) {
        likeRepository.findByArticleIdAndUserId(articleId, userId)
                .ifPresent(articleLike -> {
                    likeRepository.delete(articleLike);
                    ArticleLikeCount likeCount = likeCountRepository.findLockedByArticleId(articleId).orElseThrow();
                    likeCount.decrease();
                });
    }

    public void likeOptimisticLock(Long articleId, Long userId) {
        likeRepository.save(
                ArticleLike.create(
                        snowflake.nextId(),
                        articleId,
                        userId
                )
        );

        ArticleLikeCount likeCount = likeCountRepository.findById(articleId)
                .orElseGet(() -> likeCountRepository.save(ArticleLikeCount.init(articleId, 0L)));
        likeCount.increase();
    }

    public void unlikeOptimisticLock(Long articleId, Long userId) {
        likeRepository.findByArticleIdAndUserId(articleId, userId)
                .ifPresent(articleLike -> {
                    likeRepository.delete(articleLike);
                    ArticleLikeCount likeCount = likeCountRepository.findById(articleId).orElseThrow();
                    likeCount.decrease();
                });
    }

    public Long count(Long articleId) {
        return likeCountRepository.findById(articleId)
                .map(ArticleLikeCount::getLikeCount)
                .orElse(0L);
    }
}
