package hotel;

import room.Room;
import booking.Booking;
import java.util.ArrayList;
import java.util.List;

public class Hotel extends HotelEntity{
    private String name;
    private String location;
    private List<Room> rooms;
    private List<Booking> bookings;

    public Hotel(String id, String status, String name, String location) {
        super(id, status);
        this.name = name;
        this.location = location;
        this.rooms = new ArrayList<>();
        this.bookings = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void removeRoom(Room room) {
        rooms.remove(room);
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public void removeBooking(Booking booking) {
        bookings.remove(booking);
    }

    @Override
    public void add() {
        // Implementation for adding a hotel
    }

    @Override
    public void update() {
        
    }

    @Override
    public void delete() {
        // Implementation for deleting a hotel
    }
}
