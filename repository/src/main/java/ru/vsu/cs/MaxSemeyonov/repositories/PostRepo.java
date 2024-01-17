package ru.vsu.cs.MaxSemeyonov.repositories;

import ru.vsu.cs.MaxSemeyonov.models.Comment;
import ru.vsu.cs.MaxSemeyonov.models.Post;
import ru.vsu.cs.MaxSemeyonov.repository.Repository;

import java.util.List;

public interface PostRepo extends Repository<Post> {
    List<Post> getPostByUser(int user_id);
}
