package booking;

import room.Room;
public class Booking extends Room {
    private static int bookingCount = 0;
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
        this.bookingDate = bookingDate;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalPrice = totalPrice;
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerEmail = customerEmail;
        this.customerCity = customerCity;
        this.customerCountry = customerCountry;
        bookingCount++;
    }

    public static int getBookingCount() {
        return bookingCount;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public String getCustomerCountry() {
        return customerCountry;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingDate='" + bookingDate + '\'' +
                ", checkIn='" + checkIn + '\'' +
                ", checkOut='" + checkOut + '\'' +
                ", totalPrice=" + totalPrice +
                ", customerName='" + customerName + '\'' +
                ", customerPhoneNumber='" + customerPhoneNumber + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", customerCity='" + customerCity + '\'' +
                ", customerCountry='" + customerCountry + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Booking booking = (Booking) o;

        if (Double.compare(booking.totalPrice, totalPrice) != 0)
            return false;
        if (!bookingDate.equals(booking.bookingDate))
            return false;
        if (!checkIn.equals(booking.checkIn))
            return false;
        if (!checkOut.equals(booking.checkOut))
            return false;
        if (!customerName.equals(booking.customerName))
            return false;
        if (!customerPhoneNumber.equals(booking.customerPhoneNumber))
            return false;
        if (!customerEmail.equals(booking.customerEmail))
            return false;
        if (!customerCity.equals(booking.customerCity))
            return false;
        return customerCountry.equals(booking.customerCountry);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = bookingDate.hashCode();
        result = 31 * result + checkIn.hashCode();
        result = 31 * result + checkOut.hashCode();
        temp = Double.doubleToLongBits(totalPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + customerName.hashCode();
        result = 31 * result + customerPhoneNumber.hashCode();
        result = 31 * result + customerEmail.hashCode();
        result = 31 * result + customerCity.hashCode();
        result = 31 * result + customerCountry.hashCode();
        return result;
    }
}

