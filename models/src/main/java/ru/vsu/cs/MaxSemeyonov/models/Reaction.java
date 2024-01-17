package ru.vsu.cs.MaxSemeyonov.models;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Reaction implements Entity{
    protected Integer id = null;

    @NonNull
    protected String smile;
    @NonNull
    protected String description;
    @NonNull
    protected Integer howBad;

    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }
}