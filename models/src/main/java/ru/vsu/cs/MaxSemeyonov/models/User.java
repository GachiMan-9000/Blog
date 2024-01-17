package ru.vsu.cs.MaxSemeyonov.models;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class User implements Entity{
    protected Integer id = null;

    @NonNull
    protected String email;
    @NonNull
    protected String username;
    @NonNull
    protected String password;

    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }
}
