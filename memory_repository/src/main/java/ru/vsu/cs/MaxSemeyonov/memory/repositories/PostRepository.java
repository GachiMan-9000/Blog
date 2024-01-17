package ru.vsu.cs.MaxSemeyonov.memory.repositories;

import ru.vsu.cs.MaxSemeyonov.memory.repository.RepositoryMemory;
import ru.vsu.cs.MaxSemeyonov.models.Comment;
import ru.vsu.cs.MaxSemeyonov.models.LikeComment;
import ru.vsu.cs.MaxSemeyonov.models.Post;
import ru.vsu.cs.MaxSemeyonov.models.ReactionPost;
import ru.vsu.cs.MaxSemeyonov.repositories.LikeCommentRepo;
import ru.vsu.cs.MaxSemeyonov.repositories.PostRepo;

import java.util.ArrayList;
import java.util.List;

public class PostRepository extends RepositoryMemory<Post> implements PostRepo {
    private static PostRepository INSTANCE;

    private PostRepository(){

    }

    @Override
    protected boolean checkForeignKeys(Post item) {
        return MemoryRepositoryLib.getInstance().getUserRepo().getById(item.getUserId()) != null;
    }

    @Override
    protected void cascadeDelete(Post item) {
        List<Comment> l = new ArrayList<>(MemoryRepositoryLib.getInstance().getCommentRepo().getCommentByPost(item.getID()));
        for (Comment comment: l) {
            MemoryRepositoryLib.getInstance().getCommentRepo().delete(comment.getID());
        }

        List<ReactionPost> ll = new ArrayList<>(MemoryRepositoryLib.getInstance().getReactionPostRepo().getReactionsByPost(item.getID()));
        for (ReactionPost reactionPost: ll) {
            MemoryRepositoryLib.getInstance().getReactionPostRepo().delete(reactionPost.getID());
        }
    }

    ;

    public static PostRepository getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new PostRepository();
        }
        return INSTANCE;
    }


    @Override
    public List<Post> getPostByUser(int user_id) {
        return getAll().stream().filter(e -> e.getUserId() == user_id).toList();
    }
}
