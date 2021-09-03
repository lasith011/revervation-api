package com.enctor.booking.service;

import com.enctor.booking.model.SeatInfo;
import com.enctor.booking.model.TripInfo;

import java.util.ArrayList;
import java.util.List;

public class BookingService {
    public static List<String> getReservedSeats(SeatInfo[][] seatInfos, int passengerCount, TripInfo tripInfo, String origin, String destination) {
        int count = 1;
        List<String> seats = new ArrayList<>();

        for (int i = 0; i < seatInfos.length; ++i) {
            for (int j = 0; j < seatInfos[i].length; ++j) {
                SeatInfo seatInfo = seatInfos[i][j];
                if (seatInfo == null && passengerCount >= count) {
                    tripInfo.addSeat(SeatInfo.SEAT_ROWS.get(i), SeatInfo.SEAT_COLS.get(j), origin, destination);
                    seats.add(BookingService.getSeatNumber(i, j));
                    ++count;
                }
            }
        }

        return seats;
    }

    public static List<String> getAvailableSeats(SeatInfo[][] seatInfos) {
        List<String> seats = new ArrayList<>();
        for (int i = 0; i < seatInfos.length; ++i) {
            for (int j = 0; j < seatInfos[i].length; ++j) {
                SeatInfo seatInfo = seatInfos[i][j];
                if (seatInfo == null) {
                    seats.add(BookingService.getSeatNumber(i, j));
                }
            }
        }
        return seats;
    }

    public static String getSeatNumber(int row, int col) {
        return SeatInfo.SEAT_ROWS.get(row) + "" + SeatInfo.SEAT_COLS.get(col);
    }
}
