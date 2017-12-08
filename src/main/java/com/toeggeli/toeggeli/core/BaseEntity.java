package com.toeggeli.toeggeli.core;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public abstract class BaseEntity {
    @Id
    protected String id;


    @JsonSetter("_id")
    public void setMongoId(String _id) {
        this.id = _id;
    }
}
