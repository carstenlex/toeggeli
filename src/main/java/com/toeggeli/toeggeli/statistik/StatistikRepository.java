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
        MapReduceResults<ValueObject> teamStatistik = mongoOperations.mapReduce("matches", "classpath:matchesMapFunction.js", "classpath:matchesReduceFunction.js", ValueObject.class);

        return StreamSupport.stream(teamStatistik.spliterator(),false)
                .map(stat -> new Statistik(stat.getId(),stat.getValue().win,stat.getValue().loss))
                .collect(Collectors.toList());

    }
}
