package user;

import booking.Booking;
import interfaces.IAuthentication;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User implements IAuthentication {
    private static int customerCount = 0;
    private int customerID;
    private static List<Booking> Bookings = new ArrayList<>();

    public Customer(int customerID, String firstName, String lastName, String phoneNumber, String email, String password) {
        super(firstName, lastName, phoneNumber, email, password);
        if (customerID < 0) throw new IllegalArgumentException("Customer ID cannot be negative.");
        this.customerID = customerID;
        customerCount++;
    }

    // Getters and Setters
    public static int getCustomerCount() { return customerCount; }
    public int getCustomerID() { return customerID; }
    public void setCustomerID(int customerID) { if (customerID >= 0) this.customerID = customerID; }
    public List<Booking> getBookings() { return new ArrayList<>(Bookings); }

    

    private void addBooking(Booking booking) {
        if (booking != null && !Bookings.contains(booking)) {
            Bookings.add(booking);
            System.out.println("Booking " + booking.getBookingId() + " added for " + getFirstName());
        }
    }

    private void removeBooking(Booking booking) {
        if (booking != null && Bookings.remove(booking)) {
            System.out.println("Booking " + booking.getBookingId() + " removed for " + getFirstName());
        }
    }

    private void cancelBooking(String bookingId) {
        Booking booking = findBookingById(bookingId);
        if (booking != null && Bookings.remove(booking)) {
            booking.cancelBooking();
            System.out.println("Booking " + bookingId + " cancelled by " + getFirstName());
        } else {
            System.out.println("Booking " + bookingId + " not found for " + getFirstName());
        }
    }

    private Booking findBookingById(String bookingId) {
        for (Booking booking : Bookings) {
            if (booking.getBookingId().equals(bookingId)) return booking;
        }
        return null;
    }

    private void viewBookings() {
        if (Bookings.isEmpty()) System.out.println("No bookings for " + getFirstName() + ".");
        else {
            System.out.println("Bookings for " + getFirstName() + " " + getLastName() + ":");
            for (Booking booking : Bookings) System.out.println(booking);
        }
    }

    @Override
    public boolean checkIn(String bookingId, int roomNumber) {
        System.out.println("Check-in operation not supported for customers.");
        return false; // Or throw UnsupportedOperationException if stricter enforcement is needed
    }

    @Override
    public boolean checkOut(String bookingId, int roomNumber) {
        System.out.println("Check-out operation not supported for customers.");
        return false; // Or throw UnsupportedOperationException
    }

    @Override
    public String toString() {
        return "Customer " + customerID + ": " + getFirstName() + " " + getLastName() + " (" + getEmail() + ")";
    }
}