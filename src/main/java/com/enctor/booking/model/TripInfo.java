package com.enctor.booking.model;

import com.enctor.booking.repo.LocationManager;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class TripInfo {

    private TripInfoKey key;
    private SeatInfo[][] seats;

    public TripInfo(TripInfoKey key) {
        this.seats = new SeatInfo[SeatInfo.SEAT_ROWS.size()][SeatInfo.SEAT_COLS.size()];
        this.key = key;
    }

    public TripInfoKey getKey() {
        return this.key;
    }

    public void setKey(TripInfoKey key) {
        this.key = key;
    }

    public SeatInfo[][] getSeats() {
        return this.seats;
    }

    public void addSeat(String row, String column, String origin, String destination) {
        SeatInfo seatInfo = new SeatInfo();
        seatInfo.setRow(row);
        seatInfo.setColumn(column);
        seatInfo.setLocationInfo(LocationManager.getInstance().getLocationInfo(origin, destination));
        this.seats[SeatInfo.SEAT_ROWS.indexOf(row)][SeatInfo.SEAT_COLS.indexOf(column)] = seatInfo;
    }

}
