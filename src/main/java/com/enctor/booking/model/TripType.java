package com.enctor.booking.model;

import lombok.Getter;


@Getter
public enum TripType {
    FIRST_TRIP("08:00"),
    SECOND_TRIP("14:00");

    String startTime;

    TripType(String startTime) {
        this.startTime = startTime;
    }

    public static TripType getType(String origin, String destination) {

        return origin.toUpperCase().compareTo(destination.toUpperCase()) < 0 ? FIRST_TRIP : SECOND_TRIP;
    }
}
