package com.enctor.booking.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ReservationRequest {
    private String origin;
    private String destination;
    private String passengerCount;
    private String date;
}
