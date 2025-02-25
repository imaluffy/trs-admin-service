package com.trs.trs_admin_service.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.sql.Date;
import java.sql.Time;

@Data
@Embeddable
public class TrainStop {
    String stationName;
    Time arrivalTime;
    Integer slFare;
    Integer acFare;
    Integer AcSeatLeftFrom;
    Integer AcSeatLeftTill;
    Integer SlSeatLeftFrom;
    Integer SlSeatLeftTill;
//    Date DateOfJourney;
}
