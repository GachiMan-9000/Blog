package ru.vsu.cs.MaxSemeyonov.memory.repositories;

import ru.vsu.cs.MaxSemeyonov.memory.repository.RepositoryMemory;
import ru.vsu.cs.MaxSemeyonov.models.Comment;
import ru.vsu.cs.MaxSemeyonov.models.LikeComment;
import ru.vsu.cs.MaxSemeyonov.repositories.CommentRepo;

import java.util.ArrayList;
import java.util.List;

public class CommentRepository extends RepositoryMemory<Comment> implements CommentRepo {
    private static CommentRepository INSTANCE;

    private CommentRepository(){

    }

    @Override
    protected boolean checkForeignKeys(Comment item) {
        return MemoryRepositoryLib.getInstance().getUserRepo().getById(item.getUserId()) != null &&
                MemoryRepositoryLib.getInstance().getPostRepo().getById(item.getPostId()) != null;
    }

    @Override
    protected void cascadeDelete(Comment item) {
        List<LikeComment> l = new ArrayList<>(MemoryRepositoryLib.getInstance().getLikeCommentRepo().getLikesByComment(item.getID()));
        for (LikeComment likeComment: l) {
            MemoryRepositoryLib.getInstance().getLikeCommentRepo().delete(likeComment.getID());
        }
    }

    public static CommentRepository getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new CommentRepository();
        }
        return INSTANCE;
    }


    @Override
    public List<Comment> getCommentByPost(int post_id) {
        return getAll().stream().filter(e -> e.getPostId() == post_id).toList();
    }
}
