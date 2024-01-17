package ru.vsu.cs.MaxSemeyonov.models;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Comment implements Entity{
    protected Integer id = null;

    @NonNull
    protected String text;


    @NonNull
    protected Integer userId;
    @NonNull
    protected Integer postId;


    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }
}
