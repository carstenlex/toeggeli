package com.toeggeli.toeggeli.statistik;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class StatistikRepository {

    @Autowired
    MongoTemplate mongoOperations;


    public List<Statistik> loadTeamStatistik(){
        MapReduceResults<FromDBTeamStatistik> teamStatistik = mongoOperations.mapReduce("matches", "classpath:teamStatistikMapFunction.js", "classpath:teamStatistikReduceFunction.js", FromDBTeamStatistik.class);

        return StreamSupport.stream(teamStatistik.spliterator(),false)
                .map(stat -> new Statistik(stat.getId(),stat.getValue().win,stat.getValue().loss))
                .collect(Collectors.toList());

    }

    public List<Statistik> loadPlayerStatistik() {
        MapReduceResults<FromDBPlayerStatistik> teamStatistik = mongoOperations.mapReduce("matches", "classpath:playerStatistikMapFunction.js", "classpath:playerStatistikReduceFunction.js", FromDBPlayerStatistik.class);

        return StreamSupport.stream(teamStatistik.spliterator(),false)
                .map(stat -> new Statistik(stat.getId(),stat.getValue().win,stat.getValue().loss))
                .collect(Collectors.toList());

    }
}
