package com.trs.trs_admin_service.controller;

import com.trs.trs_admin_service.model.dto.TrainDTO;
import com.trs.trs_admin_service.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
