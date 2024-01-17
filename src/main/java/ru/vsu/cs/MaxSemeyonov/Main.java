package ru.vsu.cs.MaxSemeyonov;
import ru.vsu.cs.MaxSemeyonov.json.Deserializer;
import ru.vsu.cs.MaxSemeyonov.json.Serializer;

import ru.vsu.cs.MaxSemeyonov.memory.repositories.MemoryRepositoryLib;
import ru.vsu.cs.MaxSemeyonov.models.*;
import ru.vsu.cs.MaxSemeyonov.repositories.RepositoryLib;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void soutSomeInfo(RepositoryLib lib){
        for (User user : lib.getUserRepo().getAll()) {
            System.out.println(user.getID() + ", username:" + user.getUsername() + ", email:" + user.getEmail() + ", " +
                    "pass:" + user.getPassword());
        }
        System.out.println("Posts--------");
        for (Post post: lib.getPostRepo().getAll()) {
            System.out.println(post.getID() + ", title" + post.getTitle() + ", text" + post.getText());
            System.out.println("Wrote by " + lib.getUserRepo().getById(post.getUserId()).getUsername());
            Map<Integer, Integer> map =  lib.getReactionPostRepo().getReactionAmount(post.getID());
            for (Integer reactId: map.keySet()) {
                System.out.print(lib.getReactionRepo().getById(reactId).getSmile() + " " + map.get(reactId) + ", ");
            }
            System.out.println();
            System.out.println();
            System.out.println("Comments: ");
            for (Comment comm : lib.getCommentRepo().getCommentByPost(post.getID())) {
                System.out.println(comm.getID() + " " + comm.getText() + "   wrote by " + lib.getUserRepo().getById(comm.getUserId()).getUsername());
                System.out.println("Likes: " + lib.getLikeCommentRepo().getLikesByComment(comm.getID()).size());
            }

        }
    }
    public static void main(String[] args) {
        RepositoryLib db = MemoryRepositoryLib.getInstance();
        File file = new File("test.json");

        System.out.println("do you want to generate and write or read file? w/r");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        if (Objects.equals(s, "w")) {

            Generator generator = new Generator(db);
            generator.generate();
            soutSomeInfo(db);
            Serializer serializer = new Serializer(db);
            file = new File("test.json");
            serializer.serialize(file);

            System.out.println("wrote");
        }
        else {

            Deserializer deserializer = new Deserializer(db);
            deserializer.deserialize(file);

            System.out.println("read");
            soutSomeInfo(db);
        }
    }
}