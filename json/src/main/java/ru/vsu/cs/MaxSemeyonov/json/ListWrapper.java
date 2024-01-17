package ru.vsu.cs.MaxSemeyonov.json;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.vsu.cs.MaxSemeyonov.models.*;

import java.util.List;

@JsonTypeInfo(use = Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ListWrapper {
    private List<Comment> comments;
    private List<LikeComment> likeComments;
    private List<Post> posts;
    private List<Reaction> reactions;
    private List<ReactionPost> reactionPosts;
    private List<User> users;
}