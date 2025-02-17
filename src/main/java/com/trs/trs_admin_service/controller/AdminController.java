package com.trs.trs_admin_service.controller;

import com.trs.trs_admin_service.model.Train;
import com.trs.trs_admin_service.model.TrainRoute;
import com.trs.trs_admin_service.service.TrainRouteService;
import com.trs.trs_admin_service.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    TrainService trainService;

    @Autowired
    TrainRouteService trainRouteService;


    @PostMapping("/addTrain")
    public ResponseEntity<?> addTrain(@RequestBody Train train) {
        trainService.addTrain(train);
        return ResponseEntity.ok("Train added successfully");
    }

    @PostMapping("/addRoute")
    public ResponseEntity<?> addRoute(@RequestBody TrainRoute route) {
        trainRouteService.addRoute(route);
        return ResponseEntity.ok("Route added successfully");
    }

    @GetMapping("/findTrain/{trainNumber}")
    public ResponseEntity<?> getMapping(@PathVariable Integer trainNumber) {
        Train resp = trainService.getTrain(trainNumber);
        if(resp == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Train not found");
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        }
    }


    @GetMapping("/findRoute/{source}/{destination}")
    public ResponseEntity<?> getRoute(@PathVariable String source, @PathVariable String destination) {
        TrainRoute resp = trainRouteService.getRoute(source, destination);
        if(resp == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Route not found");
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        }
    }

}
