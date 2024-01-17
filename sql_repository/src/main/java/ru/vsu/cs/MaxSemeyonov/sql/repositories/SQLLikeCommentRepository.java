package ru.vsu.cs.MaxSemeyonov.sql.repositories;

import ru.vsu.cs.MaxSemeyonov.models.LikeComment;
import ru.vsu.cs.MaxSemeyonov.repositories.LikeCommentRepo;
import ru.vsu.cs.MaxSemeyonov.sql.repository.SQLRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/*
CREATE TABLE IF NOT EXISTS "LikeComment" (
    id SERIAL PRIMARY KEY,
    userId INTEGER REFERENCES "User"(id) ON DELETE CASCADE,
    commentId INTEGER REFERENCES "Comment"(id) ON DELETE CASCADE
);
 */
public class SQLLikeCommentRepository extends SQLRepository<LikeComment> implements LikeCommentRepo {
    private static SQLLikeCommentRepository INSTANCE;

    private SQLLikeCommentRepository(){

    }
    public static SQLLikeCommentRepository getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new SQLLikeCommentRepository();
        }
        return INSTANCE;
    }
    @Override
    protected String[] getColumnNames() {
        return new String[]{"userId", "commentId"};
    }

    @Override
    protected LikeComment getNewT(ResultSet rs) throws SQLException {
        return new LikeComment(
                rs.getInt("id"),
                rs.getInt("userId"),
                rs.getInt("commentId")
        );
    }

    @Override
    protected String[] getColumns(LikeComment object) {
        return new String[]{
                String.valueOf(object.getUserId()),
                String.valueOf(object.getCommentId())
        };
    }

    @Override
    protected String getTablename() {
        return "LikeComment";
    }

    @Override
    public List<LikeComment> getLikesByComment(int comment_id) {
        return getWithFilters("WHERE commentId = " + comment_id);
    }
}
