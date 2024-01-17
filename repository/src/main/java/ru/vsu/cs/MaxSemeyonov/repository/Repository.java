package ru.vsu.cs.MaxSemeyonov.repository;

import ru.vsu.cs.MaxSemeyonov.models.Entity;

import java.util.List;

public interface Repository<T extends Entity> {
    void add(T item);
    T getById(int id);
    List<T> getAll();
    void update(int id, T newItem) throws IllegalArgumentException;
    void delete(int id) throws IllegalArgumentException;

    void deleteAll();
}