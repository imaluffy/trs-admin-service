package com.trs.trs_admin_service.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class TrainStopDTO {

    LocalDate DateOfJourney;
    String stationName;
    Integer distanceFromSourceInKms;
    LocalTime arrivalTime;
    LocalTime departureTime;
    Double slFare;
    Double acFare;
    Integer AcSeatLeftFrom;
    Integer AcSeatLeftTill;
    Integer SlSeatLeftFrom;
    Integer SlSeatLeftTill;
}
