package com.enctor.booking.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
public class TripInfoKey {
    private Date date;
    private TripType type;

}
