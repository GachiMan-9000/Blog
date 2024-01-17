package ru.vsu.cs.MaxSemeyonov.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.vsu.cs.MaxSemeyonov.models.*;
import ru.vsu.cs.MaxSemeyonov.repositories.RepositoryLib;

import java.io.File;
import java.io.IOException;

public class Deserializer {
    protected final RepositoryLib lib;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Deserializer(RepositoryLib lib){
        this.lib = lib;
    }

    public void deserialize(File file){
        try {
            ListWrapper listWrapper = objectMapper.readValue(file, ListWrapper.class);
            for (User user : listWrapper.getUsers()) {
                lib.getUserRepo().add(user);
            }
            for (Post post : listWrapper.getPosts()) {
                lib.getPostRepo().add(post);
            }
            for (Comment comment : listWrapper.getComments()) {
                lib.getCommentRepo().add(comment);
            }
            for (LikeComment likeComment : listWrapper.getLikeComments()) {
                lib.getLikeCommentRepo().add(likeComment);
            }
            for (Reaction reaction : listWrapper.getReactions()) {
                lib.getReactionRepo().add(reaction);
            }
            for (ReactionPost reactionPost : listWrapper.getReactionPosts()) {
                lib.getReactionPostRepo().add(reactionPost);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new IllegalArgumentException("wrong file");
        }
    }
}
