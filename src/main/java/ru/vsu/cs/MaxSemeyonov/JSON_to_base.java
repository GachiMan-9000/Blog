package ru.vsu.cs.MaxSemeyonov;

import ru.vsu.cs.MaxSemeyonov.json.Deserializer;
import ru.vsu.cs.MaxSemeyonov.repositories.RepositoryLib;
import ru.vsu.cs.MaxSemeyonov.sql.repositories.SQLRepositoryLib;
import java.io.File;

import static ru.vsu.cs.MaxSemeyonov.Main.soutSomeInfo;


public class JSON_to_base {
    public static void main(String[] args) {
        File file = new File("test.json");
        RepositoryLib db = SQLRepositoryLib.getInstance();

        Deserializer deserializer = new Deserializer(db);
        deserializer.deserialize(file);

        System.out.println("read");
        soutSomeInfo(db);
    }
}
