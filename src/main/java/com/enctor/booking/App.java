package com.enctor.booking;

import com.enctor.booking.servlet.BookingServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class App {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        final ServletHandler servletHandler = new ServletHandler();
        server.setHandler(servletHandler);
        servletHandler.addServletWithMapping(BookingServlet.class, "/api/booking");
        try {
            server.start();
            server.join();
        } finally {
            server.destroy();
        }
    }
}
