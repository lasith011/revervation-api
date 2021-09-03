package com.enctor.booking.servlet;

import com.enctor.booking.response.ErrorResponse;
import com.enctor.booking.response.ReservationResponse;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.mockito.Mockito.when;

public class BookingServletTest {
    private final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
    private final Gson gson = new Gson();
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testDoGetSuccess() throws IOException, ParseException {
        when(request.getParameter("passengerCount")).thenReturn("5");
        when(request.getParameter("origin")).thenReturn("A");
        when(request.getParameter("destination")).thenReturn("D");
        when(request.getParameter("date")).thenReturn("2021-11-23");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(response.getWriter()).thenReturn(pw);
        new BookingServlet().doGet(request, response);
        String result = sw.getBuffer().toString().trim();

        ReservationResponse aRes = this.gson.fromJson(result, ReservationResponse.class);
        Assert.assertEquals("Origin should be A", "A", aRes.getOrigin());
        Assert.assertEquals("Destination should be D", "D", aRes.getDestination());
        Assert.assertEquals("Passenger count should be 5", 5, aRes.getPassengerCount());
        Assert.assertEquals("Date should be 2021-11-23", SDF.parse("2021-11-23"), aRes.getDate());
    }


    @Test
    public void testDoGetRequiredOrigin() throws IOException {

        when(request.getParameter("passengerCount")).thenReturn("5");
        when(request.getParameter("origin")).thenReturn(null);
        when(request.getParameter("destination")).thenReturn("D");
        when(request.getParameter("date")).thenReturn("2021-11-23");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(response.getWriter()).thenReturn(pw);
        new BookingServlet().doGet(request, response);
        String result = sw.getBuffer().toString().trim();

        ErrorResponse response = this.gson.fromJson(result, ErrorResponse.class);

        Assert.assertEquals("Response code should be 400", 400L, response.getCode());
        Assert.assertEquals("Response message should be 'Origin is required.'", "Origin is required.", response.getMessage());
    }

    @Test
    public void testDoGetRequiredDestination() throws IOException {

        when(request.getParameter("passengerCount")).thenReturn("5");
        when(request.getParameter("origin")).thenReturn("A");
        when(request.getParameter("destination")).thenReturn(null);
        when(request.getParameter("date")).thenReturn("2021-11-23");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(response.getWriter()).thenReturn(pw);
        new BookingServlet().doGet(request, response);
        String result = sw.getBuffer().toString().trim();


        ErrorResponse errorResponse = this.gson.fromJson(result, ErrorResponse.class);
        Assert.assertEquals("Response code should be 400", 400L, errorResponse.getCode());
        Assert.assertEquals("Response message should be 'Destination is required.'", "Destination is required.", errorResponse.getMessage());
    }

    @Test
    public void testDoPostSuccess() throws IOException, ParseException {
        when(request.getParameter("passengerCount")).thenReturn("5");
        when(request.getParameter("origin")).thenReturn("A");
        when(request.getParameter("destination")).thenReturn("D");
        when(request.getParameter("date")).thenReturn("2021-11-23");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(response.getWriter()).thenReturn(pw);
        new BookingServlet().doPost(request, response);
        String result = sw.getBuffer().toString().trim();

        ReservationResponse response = this.gson.fromJson(result, ReservationResponse.class);
        Assert.assertEquals("Origin should be A", "A", response.getOrigin());
        Assert.assertEquals("Destination should be D", "D", response.getDestination());
        Assert.assertEquals("Passenger count should be 5", 5, response.getPassengerCount());
        Assert.assertEquals("Date should be 2021-11-23", SDF.parse("2021-11-23"), response.getDate());
        Assert.assertEquals("Seat count should be 5", 5, response.getSeats().size());
    }

    @Test
    public void testDoPostRequiredOrigin() throws IOException {
        when(request.getParameter("passengerCount")).thenReturn("5");
        when(request.getParameter("origin")).thenReturn(null);
        when(request.getParameter("destination")).thenReturn("D");
        when(request.getParameter("date")).thenReturn("2021-11-23");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(response.getWriter()).thenReturn(pw);
        new BookingServlet().doPost(request, response);
        String result = sw.getBuffer().toString().trim();
        ErrorResponse response = this.gson.fromJson(result, ErrorResponse.class);
        Assert.assertEquals("Response code should be 400", 400L, response.getCode());
        Assert.assertEquals("Response message should be 'Origin is required.'", "Origin is required.", response.getMessage());
    }
}
