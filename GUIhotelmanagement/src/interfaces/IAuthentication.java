package interfaces;

public interface IAuthentication {

    // Method to check a guest into a room
    boolean checkIn(String bookingId, int roomNumber);

    // Method to check a guest out of a room
    boolean checkOut(String bookingId, int roomNumber);
}