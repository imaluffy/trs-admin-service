package com.trs.trs_admin_service.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DeleteStopRequest {
    Integer trainNumber;
    LocalDate DateOfJourney;
    String stationName;
}
