package booking;

import room.Room;

public class Booking extends Room {
    private static int bookingCount = 0;
    private String bookingId;
    private String bookingDate;
    private String checkIn;
    private String checkOut;
    private double totalPrice;
    private String customerName;
    private String customerPhoneNumber;
    private String customerEmail;
    private String customerCity;
    private String customerCountry;

    public Booking(String bookingDate, String checkIn, String checkOut, String roomNumber, String roomType,
            double price, int floor, String viewType, boolean hasBalcony, String[] roomFacilities, double totalPrice,
            String customerName, String customerPhoneNumber, String customerEmail, String customerCity,
            String customerCountry) {
        super(roomNumber, roomType, price, floor, viewType, hasBalcony, roomFacilities);
        if (totalPrice < 0)
            throw new IllegalArgumentException("Total price cannot be negative.");
        if (customerName == null || customerName.trim().isEmpty())
            throw new IllegalArgumentException("Customer name cannot be null or empty.");
        this.bookingId = "B" + String.format("%04d", ++bookingCount);
        this.bookingDate = bookingDate;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalPrice = totalPrice;
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerEmail = customerEmail;
        this.customerCity = customerCity;
        this.customerCountry = customerCountry;
        setBooked(true);
    }

    // Getters and Setters
    public String getBookingId() {
        return bookingId;
    }

    public static int getBookingCount() {
        return bookingCount;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        if (totalPrice >= 0)
            this.totalPrice = totalPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        if (customerName != null && !customerName.trim().isEmpty())
            this.customerName = customerName;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerCountry() {
        return customerCountry;
    }

    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }

    // Utility Methods
    public void cancelBooking() {
        setBooked(false);
        System.out.println("Booking " + bookingId + " cancelled for " + customerName);
    }

    public void updateBookingDates(String newCheckIn, String newCheckOut) {
        if (newCheckIn != null && newCheckOut != null) {
            this.checkIn = newCheckIn;
            this.checkOut = newCheckOut;
            System.out.println("Booking " + bookingId + " dates updated: " + checkIn + " to " + checkOut);
        }
    }

    @Override
    public String toString() {
        return "Booking " + bookingId + ": " + customerName + " | Room " + getRoomNumber() + " | Check-In: " + checkIn +
                " | Check-Out: " + checkOut + " | Total: $" + totalPrice + " | Booked on: " + bookingDate;
    }
}