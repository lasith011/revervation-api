package com.enctor.booking.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class SeatInfo {
    public static final List<String> SEAT_ROWS = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    public static final List<String> SEAT_COLS = Arrays.asList("A", "B", "C", "D");
    private String row;
    private String column;
    private LocationInfo locationInfo;
}
