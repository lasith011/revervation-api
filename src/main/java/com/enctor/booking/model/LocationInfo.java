package com.enctor.booking.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class LocationInfo {
    public static final List<String> LOCATIONS = Arrays.asList("A", "B", "C", "D");
    private BigDecimal price;
    private Double distance;
    private Integer duration;
    private String origin;
    private String destination;

    public LocationInfo(BigDecimal price, Double distance, Integer duration) {
        this.price = price;
        this.distance = distance;
        this.duration = duration;
    }
}
