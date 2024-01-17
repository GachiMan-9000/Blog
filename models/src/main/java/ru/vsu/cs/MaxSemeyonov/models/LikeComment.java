package ru.vsu.cs.MaxSemeyonov.models;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class LikeComment implements Entity{
    protected Integer id = null;

    @NonNull
    protected Integer userId;
    @NonNull
    protected Integer commentId;



    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }
}