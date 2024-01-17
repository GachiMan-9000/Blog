package ru.vsu.cs.MaxSemeyonov.sql.repositories;

import ru.vsu.cs.MaxSemeyonov.models.Comment;
import ru.vsu.cs.MaxSemeyonov.repositories.CommentRepo;
import ru.vsu.cs.MaxSemeyonov.sql.repository.SQLRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
/*
CREATE TABLE IF NOT EXISTS "Comment" (
    id SERIAL PRIMARY KEY,
    text TEXT NOT NULL,
    userId INTEGER REFERENCES "User"(id) ON DELETE CASCADE,
    postId INTEGER REFERENCES "Post"(id) ON DELETE CASCADE
);
 */
public class SQLCommentRepository extends SQLRepository<Comment> implements CommentRepo {
    private static SQLCommentRepository INSTANCE;

    private SQLCommentRepository(){

    }
    public static SQLCommentRepository getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new SQLCommentRepository();
        }
        return INSTANCE;
    }

    @Override
    public List<Comment> getCommentByPost(int post_id) {
        return getWithFilters(" WHERE postId = " + post_id);
    }

    @Override
    protected String[] getColumnNames() {
        return new String[]{"text", "userId", "postId"};
    }

    @Override
    protected Comment getNewT(ResultSet rs) throws SQLException {
        return new Comment(
                rs.getInt("id"),
                rs.getString("text"),
                rs.getInt("userId"),
                rs.getInt("postId")
        );
    }

    @Override
    protected String[] getColumns(Comment object) {
        return new String[]{
                object.getText(),
                String.valueOf(object.getUserId()),
                String.valueOf(object.getPostId())
        };
    }

    @Override
    protected String getTablename() {
        return "Comment";
    }
}
