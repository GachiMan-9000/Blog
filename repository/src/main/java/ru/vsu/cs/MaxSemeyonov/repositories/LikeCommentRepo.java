package ru.vsu.cs.MaxSemeyonov.repositories;

import ru.vsu.cs.MaxSemeyonov.models.Comment;
import ru.vsu.cs.MaxSemeyonov.models.LikeComment;
import ru.vsu.cs.MaxSemeyonov.repository.Repository;

import java.util.List;

public interface LikeCommentRepo extends Repository<LikeComment> {
    List<LikeComment> getLikesByComment(int comment_id);

}
