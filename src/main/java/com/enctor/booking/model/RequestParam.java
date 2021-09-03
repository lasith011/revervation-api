package com.enctor.booking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class RequestParam {

    private String origin;
    private String destination;
    private Date date;
    private int passengerCount;
}
