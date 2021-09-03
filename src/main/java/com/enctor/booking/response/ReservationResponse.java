package com.enctor.booking.response;

import com.enctor.booking.model.TripType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReservationResponse {

    private long id;
    private String origin;
    private String destination;
    private TripType type;
    private int passengerCount;
    private BigDecimal totalPrice;
    private Date date;
    private double distance;
    private int duration;
    private List<String> seats = new ArrayList<>();
}
