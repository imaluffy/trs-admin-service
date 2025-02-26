package com.trs.trs_admin_service.controller;

import com.trs.trs_admin_service.model.RequestTemplate;
import com.trs.trs_admin_service.model.TrainStop;
import com.trs.trs_admin_service.model.TrainStopBase;
import com.trs.trs_admin_service.service.BaseTrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/base")
public class BaseDataController {
    @Autowired
    BaseTrainService baseTrainService;

    @PostMapping("/addBaseTrain")
    ResponseEntity<?> createTrainStop(@RequestBody TrainStopBase trainStopBase){
        baseTrainService.addBaseTrain(trainStopBase);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(String.format("Base Train: %s Created Successfully", trainStopBase.getTrainName()));
    }

    @PostMapping("/newDayTrainSchedule")
    ResponseEntity<?> addTrainStopsByDate(@RequestBody RequestTemplate requestTemplate){
        Integer responseCode= baseTrainService.addTrainStopsByDate(requestTemplate);
        if(responseCode==-1){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Data already present for date - " + requestTemplate.getDateOfJourney());
        }else if(responseCode==1)
            return ResponseEntity.status(HttpStatus.CREATED).body("Added data successfully for date - " + requestTemplate.getDateOfJourney());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requested train does not exist - " + requestTemplate.getTrainNumber());
    }
}
