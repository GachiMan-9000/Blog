package ru.vsu.cs.MaxSemeyonov.sql.repositories;

import ru.vsu.cs.MaxSemeyonov.models.*;
import ru.vsu.cs.MaxSemeyonov.repositories.PostRepo;
import ru.vsu.cs.MaxSemeyonov.sql.repository.SQLRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/*
CREATE TABLE IF NOT EXISTS "Post" (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    text TEXT NOT NULL,
    userId INTEGER REFERENCES "User"(id) ON DELETE CASCADE
);
 */
public class SQLPostRepository extends SQLRepository<Post> implements PostRepo {
    private static SQLPostRepository INSTANCE;

    private SQLPostRepository(){

    }
    public static SQLPostRepository getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new SQLPostRepository();
        }
        return INSTANCE;
    }
    @Override
    protected String[] getColumnNames() {
        return new String[]{"title", "text", "userId"};
    }

    @Override
    protected Post getNewT(ResultSet rs) throws SQLException {
        return new Post(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("text"),
                rs.getInt("userId")
        );
    }

    @Override
    protected String[] getColumns(Post object) {
        return new String[]{
                object.getTitle(),
                object.getText(),
                String.valueOf(object.getUserId()),
        };
    }

    @Override
    protected String getTablename() {
        return "Post";
    }

    @Override
    public List<Post> getPostByUser(int user_id) {
        return getWithFilters("WHERE userId = " + user_id);
    }
}
