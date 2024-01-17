package ru.vsu.cs.MaxSemeyonov.sql.repositories;

import ru.vsu.cs.MaxSemeyonov.models.Reaction;
import ru.vsu.cs.MaxSemeyonov.repository.Repository;
import ru.vsu.cs.MaxSemeyonov.sql.repository.SQLRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
CREATE TABLE IF NOT EXISTS "Reaction" (
    id SERIAL PRIMARY KEY,
    smile VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    howBad INTEGER NOT NULL
);
 */
public class SQLReactionRepository extends SQLRepository<Reaction> implements Repository<Reaction> {
    private static SQLReactionRepository INSTANCE;

    private SQLReactionRepository(){

    }
    public static SQLReactionRepository getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new SQLReactionRepository();
        }
        return INSTANCE;
    }
    @Override
    protected String[] getColumnNames() {
        return new String[]{"smile", "description", "howBad"};
    }

    @Override
    protected Reaction getNewT(ResultSet rs) throws SQLException {
        return new Reaction(
                rs.getInt("id"),
                rs.getString("smile"),
                rs.getString("description"),
                rs.getInt("howBad")
        );
    }

    @Override
    protected String[] getColumns(Reaction object) {
        return new String[]{
                object.getSmile(),
                object.getDescription(),
                String.valueOf(object.getHowBad()),
        };
    }

    @Override
    protected String getTablename() {
        return "Reaction";
    }
}
