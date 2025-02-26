package com.trs.trs_admin_service.model.dto;

import com.trs.trs_admin_service.model.TrainStop;
import lombok.Data;

import java.util.List;

@Data
public class TrainDTO {
    private String trainName;
    private Integer trainNumber;
    private String trainSource;
    private String trainDestination;
    private Integer trainBaseFare;
    private Integer trainCapacity;
    private Integer trainBookedSeats;
    private String trainStatus;
    private List<TrainStop> trainStops;
}
