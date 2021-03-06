package com.toeggeli.toeggeli.statistik;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/statistik")
public class StatistikController {


    @Autowired
    StatistikRepository statistikRepository;




    @GetMapping("/ranking/team")
    public List<Statistik> getStatistik() {

        List<Statistik> statistiks = statistikRepository.loadTeamStatistik();
        return statistiks;
    }

    @GetMapping("/ranking/player")
    public List<Statistik> getStatistikPlayer() {
        List<Statistik> statistiks = statistikRepository.loadPlayerStatistik();
        return statistiks;
    }



}
