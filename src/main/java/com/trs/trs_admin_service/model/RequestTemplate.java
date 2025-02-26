package com.trs.trs_admin_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestTemplate {
    Integer trainNumber;
    LocalDate DateOfJourney;
    String stationName;
}
