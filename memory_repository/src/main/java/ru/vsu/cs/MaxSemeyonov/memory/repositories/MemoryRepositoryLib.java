package ru.vsu.cs.MaxSemeyonov.memory.repositories;

import ru.vsu.cs.MaxSemeyonov.models.Post;
import ru.vsu.cs.MaxSemeyonov.models.Reaction;
import ru.vsu.cs.MaxSemeyonov.models.User;
import ru.vsu.cs.MaxSemeyonov.repositories.*;
import ru.vsu.cs.MaxSemeyonov.repository.Repository;

public class MemoryRepositoryLib implements RepositoryLib {
    private static MemoryRepositoryLib INSTANCE;

    private MemoryRepositoryLib(){

    };

    public static MemoryRepositoryLib getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new MemoryRepositoryLib();
        }
        return INSTANCE;
    }
    @Override
    public CommentRepo getCommentRepo() {
        return CommentRepository.getInstance();
    }

    @Override
    public LikeCommentRepo getLikeCommentRepo() {
        return LikeCommentRepository.getInstance();
    }

    @Override
    public PostRepo getPostRepo() {
        return PostRepository.getInstance();
    }

    @Override
    public Repository<Reaction> getReactionRepo() {
        return ReactionRepository.getInstance();
    }

    @Override
    public ReactionPostRepo getReactionPostRepo() {
        return ReactionPostRepository.getInstance();
    }

    @Override
    public Repository<User> getUserRepo() {
        return UserRepository.getInstance();
    }
}
