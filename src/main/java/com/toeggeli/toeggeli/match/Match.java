package com.toeggeli.toeggeli.match;

import com.toeggeli.toeggeli.core.BaseEntity;
import com.toeggeli.toeggeli.team.Team;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@Document(collection="matches")
public class Match extends BaseEntity{
    private Team team1;
    private Team team2;
    private int score1, score2;
    private Date datum;
}
