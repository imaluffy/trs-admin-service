package com.trs.trs_admin_service.controller;

import com.trs.trs_admin_service.model.TrainStop;
import com.trs.trs_admin_service.model.dto.TrainDTO;
import com.trs.trs_admin_service.model.dto.TrainStopDTO;
import com.trs.trs_admin_service.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/update")
public class UpdateTrainController {

    @Autowired
    TrainService trainService;

    @PostMapping("/train")
    public ResponseEntity<?> updateTrain(@RequestBody TrainDTO trainDTO){
        TrainDTO updatedTrain= trainService.updateTrain(trainDTO);
        if(updatedTrain!=null)
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedTrain);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The train not found");
    }


    @PostMapping("/addTrainStop/{trainNum}")
    public ResponseEntity<?> addStopInTrain(@RequestBody TrainStopDTO trainStopDTO, @PathVariable Integer trainNum){
        List<TrainStop> trainStopList = trainService.addStopInTrain(trainStopDTO,trainNum);
        if(trainStopList!=null)
            return ResponseEntity.status(HttpStatus.CREATED).body(trainStopList);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requested train not found: "+trainNum);

    }

    @PostMapping("/updateTrainStop/{trainNum}")
     public ResponseEntity<?> updateStopInTrain(@RequestBody TrainStopDTO trainStopDTO, @PathVariable Integer trainNum){
        List<TrainStop>trainStopList= trainService.updateStopInTrain(trainStopDTO, trainNum);
        if(trainStopList!=null)
            return ResponseEntity.status(HttpStatus.CREATED).body(trainStopList);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requested train not found: "+trainNum);
    }

}
