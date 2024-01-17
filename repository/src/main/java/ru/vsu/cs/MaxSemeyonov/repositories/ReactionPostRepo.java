package ru.vsu.cs.MaxSemeyonov.repositories;

import ru.vsu.cs.MaxSemeyonov.models.Post;
import ru.vsu.cs.MaxSemeyonov.models.Reaction;
import ru.vsu.cs.MaxSemeyonov.models.ReactionPost;
import ru.vsu.cs.MaxSemeyonov.repository.Repository;

import java.util.List;
import java.util.Map;

public interface ReactionPostRepo extends Repository<ReactionPost> {
    List<ReactionPost> getReactionsByPost(int post_id);

    Map<Integer, Integer> getReactionAmount(int post_id);
}
