package ru.vsu.cs.MaxSemeyonov.memory.repositories;

import ru.vsu.cs.MaxSemeyonov.memory.repository.RepositoryMemory;
import ru.vsu.cs.MaxSemeyonov.models.*;

import java.util.ArrayList;
import java.util.List;

public class UserRepository extends RepositoryMemory<User> {
    private static UserRepository INSTANCE;

    private UserRepository(){

    }

    @Override
    protected boolean checkForeignKeys(User item) {
        return true;
    }

    @Override
    protected void cascadeDelete(User item) {
        List<LikeComment> likeComments = new ArrayList<>(MemoryRepositoryLib.getInstance().getLikeCommentRepo().getAll());
        for (LikeComment likeComment: likeComments) {
            if(!likeComment.getUserId().equals(item.getID())) continue;
            MemoryRepositoryLib.getInstance().getLikeCommentRepo().delete(likeComment.getID());
        }

        List<Comment> comments = new ArrayList<>(MemoryRepositoryLib.getInstance().getCommentRepo().getAll());
        for (Comment comment: comments) {
            if(!comment.getUserId().equals(item.getID())) continue;
            MemoryRepositoryLib.getInstance().getCommentRepo().delete(comment.getID());
        }

        List<Post> posts = new ArrayList<>(MemoryRepositoryLib.getInstance().getPostRepo().getAll());
        for (Post post: posts) {
            if(!post.getUserId().equals(item.getID())) continue;
            MemoryRepositoryLib.getInstance().getPostRepo().delete(post.getID());
        }

        List<ReactionPost> reactionPosts = new ArrayList<>(MemoryRepositoryLib.getInstance().getReactionPostRepo().getAll());
        for (ReactionPost reactionPost: reactionPosts) {
            if(!reactionPost.getUserId().equals(item.getID())) continue;
            MemoryRepositoryLib.getInstance().getReactionPostRepo().delete(reactionPost.getID());
        }
    }

    ;

    public static UserRepository getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new UserRepository();
        }
        return INSTANCE;
    }
}
