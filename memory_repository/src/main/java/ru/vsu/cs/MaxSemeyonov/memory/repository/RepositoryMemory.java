package ru.vsu.cs.MaxSemeyonov.memory.repository;


import ru.vsu.cs.MaxSemeyonov.models.Entity;
import ru.vsu.cs.MaxSemeyonov.repository.Repository;;

import java.util.ArrayList;
import java.util.List;

public abstract class RepositoryMemory<T extends Entity> implements Repository<T> {
    private final List<T> items;
    private int id = 0;

    public RepositoryMemory() {
        this.items = new ArrayList<>();
    }

    protected abstract boolean checkForeignKeys(T item);
    protected abstract void cascadeDelete(T item);

    public void add(T item) {
        if(!checkForeignKeys(item)) {
            throw new IllegalArgumentException("Illegal fk keys");
        }
        if(item.getID() == null) {
            item.setID(id);
        }
        items.add(item);
        id = Math.max(item.getID(), id) + 1;
    }

    public T getById(int id) {
        for (T item : items) {
            if (item.getID() == id) {
                return item;
            }
        }
        return null;
    }

    public List<T> getAll() {
        return new ArrayList<>(items);
    }

    public void update(int id, T newItem) {
        if(!checkForeignKeys(newItem)) {
            throw new IllegalArgumentException("Illegal fk keys");
        }

        newItem.setID(id);
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getID() == id) {
                items.set(i, newItem);
                return;
            }
        }
        throw new IllegalArgumentException("Object with ID " + id + " not found.");
    }

    public void delete(int id) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getID() == id) {
                cascadeDelete(items.get(i));
                items.remove(i);
                return;
            }
        }
        throw new IllegalArgumentException("Object with ID " + id + " not found.");
    }

    @Override
    public void deleteAll() {
        for (T item : items) {
            cascadeDelete(item);
        }
        items.clear();
    }
}