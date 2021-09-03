package com.enctor.booking.servlet;

import com.enctor.booking.exception.ValidationException;
import com.enctor.booking.model.LocationInfo;
import com.enctor.booking.model.RequestParam;
import com.enctor.booking.model.TripType;
import com.enctor.booking.response.ErrorResponse;
import com.enctor.booking.response.ReservationResponse;
import com.enctor.booking.validator.ReservationValidator;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Base template that will hold all the base level functions
 */
abstract class BaseServlet extends HttpServlet {
    private final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    RequestParam getValidatedParams(HttpServletRequest req) throws ValidationException, ParseException {
        String strPassengerCount = req.getParameter("passengerCount");
        String origin = req.getParameter("origin");
        String destination = req.getParameter("destination");
        String strDate = req.getParameter("date");
        ReservationValidator.validateReservationRequest(origin, destination, strDate, strPassengerCount);
        origin = origin.toUpperCase();
        destination = destination.toUpperCase();
        int passengerCount = Integer.parseInt(strPassengerCount);
        Date date = this.SDF.parse(strDate);
        return new RequestParam(origin, destination, date, passengerCount);
    }

    ReservationResponse getReserveResponse(RequestParam rParam, TripType type, LocationInfo locationInfo, List<String> seats) {
        ReservationResponse response = new ReservationResponse();
        response.setId(Calendar.getInstance().getTimeInMillis());
        response.setOrigin(rParam.getOrigin());
        response.setDestination(rParam.getDestination());
        response.setType(type);
        response.setPassengerCount(rParam.getPassengerCount());
        response.setTotalPrice(locationInfo.getPrice().multiply(BigDecimal.valueOf(rParam.getPassengerCount())));
        response.setDate(rParam.getDate());
        response.setDistance(locationInfo.getDistance());
        response.setDuration(locationInfo.getDuration());
        response.setSeats(seats);
        return response;
    }

    ErrorResponse getErrorResponse(String message) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(400);
        errorResponse.setMessage(message);
        return errorResponse;
    }
}
