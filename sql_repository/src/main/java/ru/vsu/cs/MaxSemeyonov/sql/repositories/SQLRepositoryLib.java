package ru.vsu.cs.MaxSemeyonov.sql.repositories;

import ru.vsu.cs.MaxSemeyonov.repositories.RepositoryLib;
import ru.vsu.cs.MaxSemeyonov.repositories.*;
import ru.vsu.cs.MaxSemeyonov.models.*;
import ru.vsu.cs.MaxSemeyonov.repository.Repository;

public class SQLRepositoryLib implements RepositoryLib {
    private static SQLRepositoryLib INSTANCE;

    private SQLRepositoryLib(){

    }
    public static SQLRepositoryLib getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new SQLRepositoryLib();
        }
        return INSTANCE;
    }
    @Override
    public CommentRepo getCommentRepo() {
        return SQLCommentRepository.getInstance();
    }

    @Override
    public LikeCommentRepo getLikeCommentRepo() {
        return SQLLikeCommentRepository.getInstance();
    }

    @Override
    public PostRepo getPostRepo() {
        return SQLPostRepository.getInstance();
    }

    @Override
    public Repository<Reaction> getReactionRepo() {
        return SQLReactionRepository.getInstance();
    }

    @Override
    public ReactionPostRepo getReactionPostRepo() {
        return SQLReactionPostRepository.getInstance();
    }

    @Override
    public Repository<User> getUserRepo() {
        return SQLUserRepository.getInstance();
    }
}
