package com.toeggeli.toeggeli.statistik.elo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/elo")
@CrossOrigin
public class EloController {

    @Autowired
    EloService eloService;

    @GetMapping("/simulate")
    public ResponseEntity<EloSimulationObj> simulate(){
        return ResponseEntity.ok(eloService.simulateElo());
    }
}
