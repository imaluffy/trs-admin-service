package com.trs.trs_admin_service.model;

import jakarta.persistence.Embeddable;
import lombok.*;


import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class TrainStop {

    LocalDate DateOfJourney;
    String stationName;
    LocalTime arrivalTime;
    Integer slFare;
    Integer acFare;
    Integer AcSeatLeftFrom;
    Integer AcSeatLeftTill;
    Integer SlSeatLeftFrom;
    Integer SlSeatLeftTill;
}
