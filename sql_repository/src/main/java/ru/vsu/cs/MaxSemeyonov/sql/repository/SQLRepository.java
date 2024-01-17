package ru.vsu.cs.MaxSemeyonov.sql.repository;
import ru.vsu.cs.MaxSemeyonov.sql.connectors.PostgreSQLConn;
import ru.vsu.cs.MaxSemeyonov.sql.connectors.SQLConnector;
import ru.vsu.cs.MaxSemeyonov.models.Entity;
import ru.vsu.cs.MaxSemeyonov.repository.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class SQLRepository<T extends Entity> implements Repository<T> {

    protected abstract String[] getColumnNames();

    protected abstract T getNewT(ResultSet rs) throws SQLException;

    protected abstract String[] getColumns(T object);

    protected abstract String getTablename();

    protected final SQLConnector connector;

    public SQLRepository(){
        try {
            connector = PostgreSQLConn.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    protected StringBuilder getColumnQuery(){
        StringBuilder queryString = new StringBuilder();
        for (int i = 0; i < getColumnNames().length; i++) {
            String column = getColumnNames()[i];

            queryString.append(column);

            if (i != getColumnNames().length - 1) {
                queryString.append(", ");
            }
        }
        return queryString;
    }


    @Override
    public void add(T item) {
        StringBuilder queryString = new StringBuilder("INSERT INTO \"").append(getTablename()).append("\" (");
        if(item.getID() != null) {
            queryString.append("id, ");
        }
        queryString.append(getColumnQuery()).append(" ) VALUES (");
        if(item.getID() != null) {
            queryString.append(" ").append(item.getID()).append(", ");
        }
        String[] columnValues = getColumns(item);
        for (int i = 0; i < columnValues.length; i++) {
            String column = columnValues[i];
            queryString.append("'");
            queryString.append(column);
            queryString.append("'");
            if (i != columnValues.length - 1) {
                queryString.append(", ");
            }
        }
        queryString.append(" );");

        try {
            PreparedStatement p = connector.makeUpdate(queryString.toString());
            ResultSet resultSet = p.getGeneratedKeys();

            if(!resultSet.next()) {
                throw new RuntimeException();
            }
            item.setID(resultSet.getInt(1));
            if(resultSet.next()){
                throw new RuntimeException();
            }

            p.close();

        } catch (SQLException e) {
            System.out.println(queryString);
            throw new RuntimeException(e);
        }
    }

    protected StringBuilder getSelectString(){

        StringBuilder queryString = new StringBuilder("SELECT ");
        queryString.append("id, ");
        queryString.append(getColumnQuery());
        queryString.append(" FROM \"").append(getTablename()).append("\"");
        return queryString;
    }

    @Override
    public T getById(int id) {
        StringBuilder queryString = getSelectString();
        queryString.append(" WHERE id = ");
        queryString.append(id);
        List<T> result = getByQuery(queryString.toString());
        if (result.size() > 1) {
            throw new RuntimeException();
        }
        if(result.size() == 0){
            return null;
        }
        return result.get(0);
    }
    protected List<T> getByQuery(String query){
        List<T> result = new ArrayList<>();
        try {
            ResultSet resultSet = connector.makeQuery(query);
            while (resultSet.next()) {
                T newObject = getNewT(resultSet);
                result.add(newObject);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<T> getAll() {
        String queryString = getSelectString().toString();
        return getByQuery(queryString);
    }

    protected List<T> getWithFilters(String filterString){
        String queryString = getSelectString().toString() + filterString;
        return getByQuery(queryString);
    }

    @Override
    public void update(int id, T newItem) throws IllegalArgumentException {
        StringBuilder queryString = new StringBuilder("UPDATE \"").append(getTablename()).append("\" SET ");

        for (int i = 0; i < getColumnNames().length; i++) {
            String column = getColumnNames()[i];
            queryString.append(column);
            queryString.append(" = ");
            queryString.append("'");
            queryString.append(getColumns(newItem)[i]);
            queryString.append("'");

            if (i != getColumnNames().length - 1) {
                queryString.append(", ");
            }
        }

        queryString.append(" where id = ").append(id);
        System.out.println(queryString);
        try {
            PreparedStatement p = connector.makeUpdate(queryString.toString());
            int affectedRows = p.getUpdateCount();
            if(affectedRows != 1){
                throw new IllegalArgumentException();
            }
            p.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) throws IllegalArgumentException {
        StringBuilder queryString = new StringBuilder("DELETE FROM \"").append(getTablename()).append("\" where id = ").append(id);

        try {
            PreparedStatement p = connector.makeUpdate(queryString.toString());
            int affectedRows = p.getUpdateCount();
            if(affectedRows != 1){
                throw new IllegalArgumentException();
            }
            p.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAll() {
        StringBuilder queryString = new StringBuilder("TRUNCATE \"").append(getTablename()).append("\" CASCADE");

        try {
            PreparedStatement p = connector.makeUpdate(queryString.toString());

            p.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
