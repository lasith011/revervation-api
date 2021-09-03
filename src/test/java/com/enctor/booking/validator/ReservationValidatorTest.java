package com.enctor.booking.validator;

import com.enctor.booking.exception.ValidationException;
import org.junit.Test;

public class ReservationValidatorTest {
    public ReservationValidatorTest() {
    }

    @Test(
            expected = ValidationException.class
    )
    public void testValidateAvailabilityCheckRequestWithoutOrigin() throws ValidationException {
        ReservationValidator.validateReservationRequest(null, "D", "2021-11-23", "5");
    }

    @Test(
            expected = ValidationException.class
    )
    public void testValidateAvailabilityCheckRequestWithoutDestination() throws ValidationException {
        ReservationValidator.validateReservationRequest("A", null, "2021-11-23", "5");
    }
}

