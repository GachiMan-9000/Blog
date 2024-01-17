package ru.vsu.cs.MaxSemeyonov.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import ru.vsu.cs.MaxSemeyonov.repositories.RepositoryLib;

import java.io.File;
import java.io.IOException;

public class Serializer {
    protected final RepositoryLib lib;
    private final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();

    public Serializer(RepositoryLib lib){
        this.lib = lib;
    }

    public void serialize(File file){
        ListWrapper listWrapper = new ListWrapper(
                lib.getCommentRepo().getAll(),
                lib.getLikeCommentRepo().getAll(),
                lib.getPostRepo().getAll(),
                lib.getReactionRepo().getAll(),
                lib.getReactionPostRepo().getAll(),
                lib.getUserRepo().getAll()
        );
        try {
            objectWriter.writeValue(file, listWrapper);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new IllegalArgumentException("wrong file");
        }
    }
}
