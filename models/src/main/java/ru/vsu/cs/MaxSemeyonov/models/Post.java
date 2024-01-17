package ru.vsu.cs.MaxSemeyonov.models;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Post implements Entity{
    protected Integer id = null;

    @NonNull
    protected String title;
    @NonNull
    protected String text;
    @NonNull
    protected Integer userId;


    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }
}
