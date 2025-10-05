package kuke.board.comment.service;

import kuke.board.comment.entity.Comment;
import kuke.board.comment.repository.CommentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @InjectMocks
    CommentService commentService;

    @Mock
    CommentRepository commentRepository;

    @DisplayName("삭제할_댓글이_자식_있으면_삭제_표시만_한다")
    @Test
    void 삭제할_댓글이_자식_있으면_삭제_표시만_한다() {
        // given
        Long articleId = 1L;
        Long commentId = 2L;
        Comment comment = createComment(articleId, commentId);
        given(commentRepository.findById(commentId))
                .willReturn(Optional.of(comment));
        given(commentRepository.countBy(articleId, commentId, 2L))
                .willReturn(2L);

        // when
        commentService.delete(commentId);

        // then
        verify(comment).delete();
    }


    @DisplayName("하위_댓글이_삭제되고_삭제되지_않는_부모면_하위_댓글만_삭제한다")
    @Test
    void 하위_댓글이_삭제되고_삭제되지_않는_부모면_하위_댓글만_삭제한다() {
        // given
        Long articleId = 1L;
        Long commentId = 2L;
        Long parentCommentId = 1L;

        Comment comment = createComment(articleId, commentId, parentCommentId);
        given(comment.isRoot())
                .willReturn(false);

        Comment parent = mock(Comment.class);
        given(parent.getDeleted())
                .willReturn(false);

        given(commentRepository.findById(commentId))
                .willReturn(Optional.of(comment));
        given(commentRepository.countBy(articleId, commentId, 2L))
                .willReturn(1L);

        given(commentRepository.findById(parentCommentId))
                .willReturn(Optional.of(parent));

        // when
        commentService.delete(commentId);

        // then
        verify(commentRepository).delete(comment);
        verify(commentRepository, never()).delete(parent);
    }

    @DisplayName("하위_댓글이_삭제되고_삭제된_부모면_재귀적으로_모두_삭제한다")
    @Test
    void 하위_댓글이_삭제되고_삭제된_부모면_재귀적으로_모두_삭제한다() {
        // given
        Long articleId = 1L;
        Long commentId = 2L;
        Long parentCommentId = 1L;

        Comment comment = createComment(articleId, commentId, parentCommentId);
        given(comment.isRoot())
                .willReturn(false);

        Comment parent = createComment(articleId, parentCommentId);
        given(parent.isRoot())
                .willReturn(true);
        given(parent.getDeleted())
                .willReturn(true);

        given(commentRepository.findById(commentId))
                .willReturn(Optional.of(comment));
        given(commentRepository.countBy(articleId, commentId, 2L))
                .willReturn(1L);

        given(commentRepository.findById(parentCommentId))
                .willReturn(Optional.of(parent));
        given(commentRepository.countBy(articleId, parentCommentId, 2L))
                .willReturn(1L);

        // when
        commentService.delete(commentId);

        // then
        verify(commentRepository).delete(comment);
        verify(commentRepository).delete(parent);
    }

    private Comment createComment(Long articleId, Long commentId) {
        Comment comment = mock(Comment.class);
        given(comment.getArticleId()).willReturn(articleId);
        given(comment.getCommentId()).willReturn(commentId);
        return comment;
    }

    private Comment createComment(Long articleId, Long commentId, Long parentCommentId) {
        Comment comment = createComment(articleId, commentId);
        given(comment.getParentCommentId()).willReturn(parentCommentId);
        return comment;
    }
}