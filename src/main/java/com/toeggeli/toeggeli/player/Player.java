package com.toeggeli.toeggeli.player;

import com.toeggeli.toeggeli.core.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "players")
@NoArgsConstructor
@Data
public class Player extends BaseEntity{

    public Player(String name) {
        this.name = name;
    }

    private String name;
}
