package ru.vsu.cs.MaxSemeyonov.repositories;

import ru.vsu.cs.MaxSemeyonov.models.Comment;
import ru.vsu.cs.MaxSemeyonov.repository.Repository;

import java.util.List;

public interface CommentRepo extends Repository<Comment> {
    List<Comment> getCommentByPost(int post_id);

}
