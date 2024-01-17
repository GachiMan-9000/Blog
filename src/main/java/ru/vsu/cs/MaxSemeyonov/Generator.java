package ru.vsu.cs.MaxSemeyonov;


import ru.vsu.cs.MaxSemeyonov.models.*;
import ru.vsu.cs.MaxSemeyonov.repositories.RepositoryLib;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class Generator {
    protected final RepositoryLib lib;
    protected final Random random;

    public Generator(RepositoryLib lib){
        this.lib = lib;
        random = new Random();
    }

    protected Integer pickRandom(List<Integer> list){
        return list.get(random.nextInt(list.size()));
    }



    public void generate(){
        // user


        // user 100
        String[] firstNames = {"John", "Jane", "Michael", "Emily", "David", "Olivia", "James", "Sophia", "William", "Emma"};
        String[] lastNames = {"Smith", "Johnson", "Brown", "Taylor", "Anderson", "Wilson", "Miller", "Davis", "Garcia", "Martinez"};
        for (String first : firstNames) {
            for (String last : lastNames) {
                User user = new User(first+last+"@gmail.com", first + "_" + last + ")", Integer.toString(random.nextInt(10000)));
                lib.getUserRepo().add(user);
            }
        }
        List<Integer> userIds = lib.getUserRepo().getAll().stream().map(User::getID).toList();
        // post
        for (int i = 0; i < 300; i++) {
            User user = lib.getUserRepo().getById(pickRandom(userIds));

            Post post = new Post("TiTlE for post" + i, "Great post from " + user.getUsername() + " with love",
                    user.getID());
            lib.getPostRepo().add(post);

        }
        List<Integer> postIds = lib.getPostRepo().getAll().stream().map(Post::getID).toList();

        // comment
        for (int i = 0; i < 1000; i++) {
            User user = lib.getUserRepo().getById(pickRandom(userIds));
            Post post = lib.getPostRepo().getById(pickRandom(postIds));


            Comment comment = new Comment("I " + user.getUsername() + "love THIS " +
                    lib.getUserRepo().getById(post.getUserId()).getUsername() + "USER!!!!",
                    user.getID(),
                    post.getID());
            lib.getCommentRepo().add(comment);

        }
        List<Integer> commentIds = lib.getCommentRepo().getAll().stream().map(Comment::getID).toList();

        // like comment
        for (int i = 0; i < 1000; i++) {
            User user = lib.getUserRepo().getById(pickRandom(userIds));
            Comment comment = lib.getCommentRepo().getById(pickRandom(commentIds));


            LikeComment likeComment = new LikeComment(user.getID(), comment.getID());
            lib.getLikeCommentRepo().add(likeComment);

        }
        // reaction
        String[][] reactions = new String[][]{
                {"<3", "Love", "5"},
                {":)", "Funny", "2"},
                {":(", "Bad", "-2"},
                {":(((", "Very bad", "-4"},
        };
        for (String[] react : reactions) {
            Reaction reaction = new Reaction(react[0], react[1], Integer.parseInt(react[2]));
            lib.getReactionRepo().add(reaction);
        }
        List<Integer> reactIds = lib.getReactionRepo().getAll().stream().map(Reaction::getID).toList();
        // reactionPost
        for (int i = 0; i < 1000000; i++) {
            ReactionPost reactionPost = new ReactionPost(pickRandom(userIds), pickRandom(postIds), pickRandom(reactIds));
            lib.getReactionPostRepo().add(reactionPost);
        }

    }
}
