package ru.vsu.cs.MaxSemeyonov.sql.repositories;

import ru.vsu.cs.MaxSemeyonov.models.User;
import ru.vsu.cs.MaxSemeyonov.repository.Repository;
import ru.vsu.cs.MaxSemeyonov.sql.repository.SQLRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
CREATE TABLE IF NOT EXISTS "User" (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);
 */
public class SQLUserRepository extends SQLRepository<User> implements Repository<User> {
    private static SQLUserRepository INSTANCE;

    private SQLUserRepository(){

    }
    public static SQLUserRepository getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new SQLUserRepository();
        }
        return INSTANCE;
    }
    @Override
    protected String[] getColumnNames() {
        return new String[]{"email", "username", "password"};
    }

    @Override
    protected User getNewT(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("email"),
                rs.getString("username"),
                rs.getString("password")
        );
    }

    @Override
    protected String[] getColumns(User object) {
        return new String[]{
                object.getEmail(),
                object.getUsername(),
                object.getPassword()
        };
    }

    @Override
    protected String getTablename() {
        return "User";
    }
}
