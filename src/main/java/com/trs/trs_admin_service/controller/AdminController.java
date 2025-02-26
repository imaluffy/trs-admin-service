package com.trs.trs_admin_service.controller;

import com.trs.trs_admin_service.model.RequestTemplate;
import com.trs.trs_admin_service.model.TrainStop;
import com.trs.trs_admin_service.model.dto.TrainDTO;
import com.trs.trs_admin_service.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    TrainService trainService;


    @PostMapping("/addTrain")
    public ResponseEntity<?> addTrain(@RequestBody TrainDTO train) {
        return ResponseEntity.status(HttpStatus.CREATED).body(trainService.addTrain(train));
    }


    @GetMapping("/findTrain/{trainNumber}")
    public ResponseEntity<?> getTrain(@PathVariable Integer trainNumber) {
        TrainDTO resp = trainService.getTrain(trainNumber);
        if(resp == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Train not found");
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        }
    }


    @DeleteMapping("/removeTrain/{trainNumber}")
    public ResponseEntity<?> deleteTrain(@PathVariable Integer trainNumber) {
        trainService.removeTrain(trainNumber);
        return ResponseEntity.status(HttpStatus.OK).body("Train removed");
    }

    @DeleteMapping("/removeTrainStop")
    public ResponseEntity<?> deleteTrainStop(@RequestBody RequestTemplate requestTemplate) {
        List<TrainStop> resp = trainService.removeTrainStop(requestTemplate);
        if(resp == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Train not found");
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        }
    }

    @DeleteMapping("/removeTrainStoByStation")
    public ResponseEntity<?> deleteTrainStopByStation(@RequestBody RequestTemplate requestTemplate){
        List<TrainStop> resp = trainService.removeTrainStopByStation(requestTemplate);
        if(resp == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Train not found");
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        }
    }



}
