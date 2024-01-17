package ru.vsu.cs.MaxSemeyonov.sql.repositories;

import ru.vsu.cs.MaxSemeyonov.models.ReactionPost;
import ru.vsu.cs.MaxSemeyonov.repositories.ReactionPostRepo;
import ru.vsu.cs.MaxSemeyonov.sql.repository.SQLRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
CREATE TABLE IF NOT EXISTS "ReactionPost" (
    id SERIAL PRIMARY KEY,
    userId INTEGER REFERENCES "User"(id) ON DELETE CASCADE,
    postId INTEGER REFERENCES "Post"(id) ON DELETE CASCADE,
    reactionId INTEGER REFERENCES "Reaction"(id) ON DELETE CASCADE
);

 */
public class SQLReactionPostRepository extends SQLRepository<ReactionPost> implements ReactionPostRepo {
    private static SQLReactionPostRepository INSTANCE;

    private SQLReactionPostRepository(){

    }
    public static SQLReactionPostRepository getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new SQLReactionPostRepository();
        }
        return INSTANCE;
    }
    @Override
    protected String[] getColumnNames() {
        return new String[]{"userId", "postId", "reactionId"};
    }

    @Override
    protected ReactionPost getNewT(ResultSet rs) throws SQLException {
        return new ReactionPost(
                rs.getInt("id"),
                rs.getInt("userId"),
                rs.getInt("postId"),
                rs.getInt("reactionId")
        );
    }

    @Override
    protected String[] getColumns(ReactionPost object) {
        return new String[]{
                String.valueOf(object.getUserId()),
                String.valueOf(object.getPostId()),
                String.valueOf(object.getReactionId()),
        };
    }

    @Override
    protected String getTablename() {
        return "ReactionPost";
    }

    @Override
    public List<ReactionPost> getReactionsByPost(int post_id) {
        return getWithFilters("WHERE postId = " + post_id);
    }

    @Override
    public Map<Integer, Integer> getReactionAmount(int post_id) {
        // TODO sql join
        List<ReactionPost> postReactions = getReactionsByPost(post_id);
        Map<Integer, Integer> map = new HashMap<>();
        for (ReactionPost reactionOnPost : postReactions) {
            map.putIfAbsent(reactionOnPost.getReactionId(), 0);

            map.put(reactionOnPost.getReactionId(), map.get(reactionOnPost.getReactionId()) + 1);
        }
        return map;
       // return null;
    }
}
