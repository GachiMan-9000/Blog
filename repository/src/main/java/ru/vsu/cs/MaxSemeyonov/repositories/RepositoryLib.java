package ru.vsu.cs.MaxSemeyonov.repositories;

import ru.vsu.cs.MaxSemeyonov.models.*;
import ru.vsu.cs.MaxSemeyonov.repository.Repository;

public interface RepositoryLib {
    CommentRepo getCommentRepo();
    LikeCommentRepo getLikeCommentRepo();
    PostRepo getPostRepo();
    Repository<Reaction> getReactionRepo();
    ReactionPostRepo getReactionPostRepo();
    Repository<User> getUserRepo();
}
