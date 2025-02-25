package com.trs.trs_admin_service.controller;

import com.trs.trs_admin_service.model.TrainStopBase;
import com.trs.trs_admin_service.model.dto.TrainStopDTO;
import com.trs.trs_admin_service.repo.BaseTrainRepository;
import com.trs.trs_admin_service.service.BaseTrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base")
public class BaseDataController {
    @Autowired
    BaseTrainService baseTrainService;

    @PostMapping("/addBaseTrain")
    ResponseEntity<?> createTrainStop(@RequestBody TrainStopBase trainStopBase){
        baseTrainService.addBaseTrain(trainStopBase);
        return ResponseEntity.status(HttpStatus.CREATED).body("Base Train Created Successfully");
    }
}
