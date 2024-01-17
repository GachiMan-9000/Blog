package ru.vsu.cs.MaxSemeyonov.memory.repositories;

import ru.vsu.cs.MaxSemeyonov.memory.repository.RepositoryMemory;
import ru.vsu.cs.MaxSemeyonov.models.Comment;
import ru.vsu.cs.MaxSemeyonov.models.LikeComment;
import ru.vsu.cs.MaxSemeyonov.repositories.CommentRepo;
import ru.vsu.cs.MaxSemeyonov.repositories.LikeCommentRepo;

import java.util.List;

public class LikeCommentRepository extends RepositoryMemory<LikeComment> implements LikeCommentRepo {
    private static LikeCommentRepository INSTANCE;

    private LikeCommentRepository(){

    }

    @Override
    protected boolean checkForeignKeys(LikeComment item) {
        return MemoryRepositoryLib.getInstance().getUserRepo().getById(item.getUserId()) != null &&
                MemoryRepositoryLib.getInstance().getCommentRepo().getById(item.getCommentId()) != null;
    }

    @Override
    protected void cascadeDelete(LikeComment item) {

    }

    public static LikeCommentRepository getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new LikeCommentRepository();
        }
        return INSTANCE;
    }

    @Override
    public List<LikeComment> getLikesByComment(int comment_id) {
        return getAll().stream().filter(e -> e.getCommentId() == comment_id).toList();
    }
}
