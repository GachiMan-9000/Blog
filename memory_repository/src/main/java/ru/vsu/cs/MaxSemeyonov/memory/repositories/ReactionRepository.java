package ru.vsu.cs.MaxSemeyonov.memory.repositories;

import ru.vsu.cs.MaxSemeyonov.memory.repository.RepositoryMemory;
import ru.vsu.cs.MaxSemeyonov.models.Comment;
import ru.vsu.cs.MaxSemeyonov.models.Post;
import ru.vsu.cs.MaxSemeyonov.models.Reaction;
import ru.vsu.cs.MaxSemeyonov.models.ReactionPost;
import ru.vsu.cs.MaxSemeyonov.repositories.PostRepo;

import java.util.ArrayList;
import java.util.List;

public class ReactionRepository extends RepositoryMemory<Reaction> {
    private static ReactionRepository INSTANCE;

    private ReactionRepository(){

    }

    @Override
    protected boolean checkForeignKeys(Reaction item) {
        return true;
    }

    @Override
    protected void cascadeDelete(Reaction item) {
        List<ReactionPost> l = new ArrayList<>(MemoryRepositoryLib.getInstance().getReactionPostRepo().getAll());
        for (ReactionPost reactionPost: l) {
            if(!reactionPost.getReactionId().equals(item.getID())) continue;
            MemoryRepositoryLib.getInstance().getReactionPostRepo().delete(reactionPost.getID());
        }

    }

    ;

    public static ReactionRepository getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new ReactionRepository();
        }
        return INSTANCE;
    }
}
