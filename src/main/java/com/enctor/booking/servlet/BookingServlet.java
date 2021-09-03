package com.enctor.booking.servlet;

import com.enctor.booking.exception.ValidationException;
import com.enctor.booking.model.LocationInfo;
import com.enctor.booking.model.RequestParam;
import com.enctor.booking.model.SeatInfo;
import com.enctor.booking.model.TripInfo;
import com.enctor.booking.model.TripInfoKey;
import com.enctor.booking.model.TripType;
import com.enctor.booking.repo.LocationManager;
import com.enctor.booking.response.ErrorResponse;
import com.enctor.booking.response.ReservationResponse;
import com.enctor.booking.service.BookingService;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Log4j2
public class BookingServlet extends BaseServlet {

    private static final long serialVersionUID = -7486796581421733338L;
    private final Map<TripInfoKey, TripInfo> tripMap = new ConcurrentHashMap<>();
    private final Gson gson = new Gson();


    public BookingServlet() {
    }

    /**
     * Check availability of seats
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            RequestParam rParam = this.getValidatedParams(req);
            TripType type = TripType.getType(rParam.getOrigin(), rParam.getDestination());
            LocationInfo locationInfo = LocationManager.getInstance().getLocationInfo(rParam.getOrigin(), rParam.getDestination());
            TripInfoKey tripInfoKey = new TripInfoKey(rParam.getDate(), type);
            TripInfo tripInfo = this.tripMap.get(tripInfoKey);
            if (tripInfo == null) {
                tripInfo = new TripInfo(tripInfoKey);
            }
            List<String> seats = BookingService.getAvailableSeats(tripInfo.getSeats());
            ReservationResponse reservationResponse = this.getReserveResponse(rParam, type, locationInfo, seats);
            res.setStatus(200);
            res.setContentType("application/json");
            res.getWriter().write(this.gson.toJson(reservationResponse));
        } catch (ValidationException | ParseException exception) {
            ErrorResponse eRes = this.getErrorResponse(exception.getMessage());
            res.setStatus(400);
            res.getWriter().write(this.gson.toJson(eRes));
            log.log(Level.FATAL, "Validation error occurred : ", exception);
        }

    }

    /**
     * Reservation of seats
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            RequestParam params = this.getValidatedParams(req);
            TripType type = TripType.getType(params.getOrigin(), params.getDestination());
            TripInfoKey tripInfoKey = new TripInfoKey(params.getDate(), type);
            synchronized (this.tripMap) {
                TripInfo tripInfo = this.tripMap.get(tripInfoKey);
                if (tripInfo == null) {
                    tripInfo = new TripInfo(tripInfoKey);
                    this.tripMap.put(tripInfoKey, tripInfo);
                }

                SeatInfo[][] seatInfos = tripInfo.getSeats();
                List<String> seats = BookingService.getReservedSeats(seatInfos, params.getPassengerCount(), tripInfo, params.getOrigin(), params.getDestination());
                LocationInfo locationInfo = LocationManager.getInstance().getLocationInfo(params.getOrigin(), params.getDestination());
                ReservationResponse reserveRes = this.getReserveResponse(params, type, locationInfo, seats);
                res.setContentType("application/json");
                res.getWriter().write(this.gson.toJson(reserveRes));
            }
        } catch (ValidationException | ParseException exception) {
            ErrorResponse errorResponse = this.getErrorResponse(exception.getMessage());
            res.setStatus(400);
            res.getWriter().write(this.gson.toJson(errorResponse));
            log.log(Level.FATAL, "Validation error occurred : ", exception);
        }
    }
}
