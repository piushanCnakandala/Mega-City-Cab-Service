package com.example.megacitycabservice.controller;

import com.example.megacitycabservice.dao.BookingDAO;
import com.example.megacitycabservice.dao.CarDAO;
import com.example.megacitycabservice.dao.CustomerUserDAO;
import com.example.megacitycabservice.dao.DriverDAO;
import com.example.megacitycabservice.enums.CarStatus;
import com.example.megacitycabservice.enums.UserStatus;
import com.example.megacitycabservice.model.Booking;
import com.example.megacitycabservice.model.Car;
import com.example.megacitycabservice.model.CustomerUser;
import com.example.megacitycabservice.model.Driver;
import com.example.megacitycabservice.service.BookingService;
import com.example.megacitycabservice.service.CarService;
import com.example.megacitycabservice.service.CustomerUserService;
import com.example.megacitycabservice.service.DriverService;
import com.example.megacitycabservice.util.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/bookings")
public class BookingServlet extends HttpServlet {

    private BookingService bookingService;
    private CarService carService;
    private CustomerUserService customerService;
    private DriverService driverService;
   // private PaymentService paymentService;

    public BookingServlet(){
        super();
    }

    @Override
    public void init() throws ServletException {
        try {

            DBConnection dbConnection = DBConnection.getInstance();
            Connection connection = dbConnection.getConnection();

            bookingService = new BookingService(new BookingDAO(connection));
            carService = new CarService(new CarDAO(connection));
            customerService = new CustomerUserService(new CustomerUserDAO(connection));
            driverService = new DriverService(new DriverDAO(connection));
            //paymentService = new PaymentService(new PaymentDAO(connection));

        } catch (SQLException e) {
            throw new ServletException("Unable to connect to database", e);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            try {
                handleAddBooking(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if ("update".equals(action)) {
           // handleUpdateBooking(request, response);
        } else if ("delete".equals(action)) {
           // handleDeleteBooking(request, response);
        } else if ("pay".equals(action)) {  // New action for processing payments
          /*  try {
               // handlePayment(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }*/
        } else {
            response.sendRedirect("bookings.jsp?error=1");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookingNumber = request.getParameter("bookingNumber");

        if (bookingNumber == null || bookingNumber.isEmpty()) {
            try {
                System.out.println("get all  methods");
                List<Booking> bookings = bookingService.getAllBookings();
                request.setAttribute("bookings", bookings);
                System.out.println("get all bookings");


                List<Car> availableCars = carService.getAllCarsWhereStatus(UserStatus.AVAILABLE.name());
                request.setAttribute("availableCars", availableCars);

                request.setAttribute("registeredCustomers", customerService.getAllCustomers());

                // Fetch available drivers
                List<Driver> allDriversWhereStatus = driverService.getAllDriversWhereStatus();
                request.setAttribute("availableDrivers", allDriversWhereStatus);

                // Forward to the JSP page
                request.getRequestDispatcher("bookings.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("bookings.jsp?error=1");
            }
            return;
        }

        try {
            //generatePdf(response, bookingNumber);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("bookings.jsp?error=Error generating receipt");
        }
    }

    private void handleAddBooking(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        int customerRegNum = Integer.parseInt(request.getParameter("customerRegistrationNumber"));
        String startingMeterKm = request.getParameter("startingMeterKm");
        LocalDate bookingDate = LocalDate.parse(request.getParameter("bookingDate"));
        LocalDate returnDate = LocalDate.parse(request.getParameter("returnDate"));
        int carId = Integer.parseInt(request.getParameter("id"));
        String driverId = request.getParameter("driverId");
        String status = request.getParameter("status");

        System.out.println("Fetching customer with registration number: " + customerRegNum);

        CustomerUser customer = customerService.getCustomerByRegistrationNumber(customerRegNum);

        if (customer == null) {
            System.out.println("Customer not found: " + customerRegNum);
            response.sendRedirect("bookings.jsp?error=Customer not found");
            return;
        }

        System.out.println("Fetching Car with ID: " + carId);
        Car car = carService.getCarById(carId);
        if (car == null) {
            System.out.println("Car not found: " + carId);
            response.sendRedirect("bookings.jsp?error=Car not found");
            return;
        }

        System.out.println("Fetching Car with ID: " + driverId);
        Driver driver = driverService.getDriverById(driverId);
        if (driver == null) {
            System.out.println("driver not found: " + carId);
            response.sendRedirect("bookings.jsp?error=driver not found");
            return;
        }


        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setStartingMeterKm(Double.parseDouble(startingMeterKm));
        booking.setBookingDate(bookingDate);
        booking.setReturnDate(returnDate);
        booking.setCar(car);
        booking.setDriver(driver);
        booking.setStatus(status);

        try {
            bookingService.addBooking(booking);
            carService.updateCarStatus(carId, CarStatus.BOOKED.name());
            driverService.updateDriverStatus(driverId, UserStatus.BOOKED.name());

            response.sendRedirect("bookings");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("bookings.jsp?error=1");
        }
    }

/*  private void handlePaymentBooking(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        double startingMeterKm = Double.parseDouble(request.getParameter("startingMeterKm"));
        double endingMeterKm = Double.parseDouble(request.getParameter("startingMeterKm"));
        double priceOfOneKm = Double.parseDouble(request.getParameter("price"));

        double fare = distance * 2;

        Booking booking = new Booking();
        booking.setBookingNumber(bookingNumber);
        booking.setCustomer(new Customer(customerName));
        booking.setDestination(destination);
        booking.setDistance(distance);
        booking.setFare(fare);

        try {
            bookingService.updateBooking(booking);
            response.sendRedirect("bookings?success=1");
        } catch (SQLException e) {
            response.sendRedirect("bookings?error=1");
        }
    }*/

 /*   private void handleDeleteBooking(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String bookingNumber = request.getParameter("bookingNumber");

        try {
            bookingService.deleteBooking(bookingNumber);
            response.sendRedirect("bookings?success=1");
        } catch (SQLException e) {
            response.sendRedirect("bookings?error=1");
        }
    }*/

   /* private void handlePayment(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String bookingNumber = request.getParameter("bookingNumber");
        String customerRegNum = request.getParameter("customerRegistrationNumber");
        String carId = request.getParameter("carId");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String driverId = request.getParameter("driverId");

        // Fetch the customer and validate the booking
        Customer customer = customerService.getCustomerByRegistrationNumber(customerRegNum);
        if (customer == null) {
            response.sendRedirect("bookings.jsp?error=Customer not found");
            return;
        }

        // Process the payment
        Payment payment = new Payment();
        payment.setBookingNumber(bookingNumber);
        payment.setCustomerId(customer.getRegistrationNumber());
        payment.setCarId(carId);
        payment.setAmount(amount);
        payment.setStatus(Status.PAID.name());

        // Save payment
        paymentService.addPayment(payment);

        // Release the car and driver, making them available
        carService.updateCarStatus(carId, Status.AVAILABLE.name());
        driverService.updateDriverStatus(driverId, Status.AVAILABLE.name());

        // Redirect to bookings page after payment
        response.sendRedirect("bookings?success=Payment successful");
    }*/


}
