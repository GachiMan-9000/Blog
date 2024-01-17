
package ru.vsu.cs.MaxSemeyonov.memory.repositories;

import ru.vsu.cs.MaxSemeyonov.memory.repository.RepositoryMemory;
import ru.vsu.cs.MaxSemeyonov.models.Reaction;
import ru.vsu.cs.MaxSemeyonov.models.ReactionPost;
import ru.vsu.cs.MaxSemeyonov.repositories.ReactionPostRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReactionPostRepository extends RepositoryMemory<ReactionPost> implements ReactionPostRepo {
    private static ReactionPostRepository INSTANCE;

    private ReactionPostRepository(){

    }

    @Override
    protected boolean checkForeignKeys(ReactionPost item) {
        return MemoryRepositoryLib.getInstance().getUserRepo().getById(item.getUserId()) != null &&
                MemoryRepositoryLib.getInstance().getPostRepo().getById(item.getPostId()) != null &&
                MemoryRepositoryLib.getInstance().getReactionRepo().getById(item.getReactionId()) != null ;
    }

    @Override
    protected void cascadeDelete(ReactionPost item) {

    }

    public static ReactionPostRepository getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new ReactionPostRepository();
        }
        return INSTANCE;
    }

    @Override
    public List<ReactionPost> getReactionsByPost(int post_id) {
        return getAll().stream().filter(e -> e.getPostId() == post_id).toList();
    }

    @Override
    public Map<Integer, Integer> getReactionAmount(int post_id) {
        List<ReactionPost> postReactions = getReactionsByPost(post_id);
        Map<Integer, Integer> map = new HashMap<>();
        for (ReactionPost reactionOnPost : postReactions) {
            map.putIfAbsent(reactionOnPost.getReactionId(), 0);

            map.put(reactionOnPost.getReactionId(), map.get(reactionOnPost.getReactionId()) + 1);
        }
        return map;
    }
}
