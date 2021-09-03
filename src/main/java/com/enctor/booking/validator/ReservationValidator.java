package com.enctor.booking.validator;

import com.enctor.booking.exception.ValidationException;
import com.enctor.booking.model.LocationInfo;
import org.eclipse.jetty.util.StringUtil;

public final class ReservationValidator {
    public ReservationValidator() {
    }

    public static void validateReservationRequest(String origin, String destination, String strDate, String strPassengerCount) throws ValidationException {
        if (!StringUtil.isNotBlank(origin)) {
            throw new ValidationException("Origin is required.");
        } else if (!StringUtil.isNotBlank(destination)) {
            throw new ValidationException("Destination is required.");
        } else if (!LocationInfo.LOCATIONS.contains(origin.toUpperCase())) {
            throw new ValidationException("Invalid origin.");
        } else if (!LocationInfo.LOCATIONS.contains(destination.toUpperCase())) {
            throw new ValidationException("Invalid destination.");
        } else if (!StringUtil.isNotBlank(strDate)) {
            throw new ValidationException("Date is required.");
        } else if (!strDate.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            throw new ValidationException("Invalid date format.");
        } else if (StringUtil.isNotBlank(strPassengerCount) && Integer.parseInt(strPassengerCount) < 0) {
            throw new ValidationException("Passenger count should be positive number.");
        } else if (origin.equalsIgnoreCase(destination)) {
            throw new ValidationException("Origin and destination cannot be same.");
        }
    }
}
