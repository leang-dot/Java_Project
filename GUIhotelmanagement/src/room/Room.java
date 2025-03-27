package room;

import java.util.Arrays;
import java.util.Objects;

public class Room {
    private String roomNumber;
    private String roomType;
    private double price;
    private boolean isBooked; 
   
    public Room(String roomNumber, String roomType, double price, int floor, String viewType, boolean hasBalcony,
            String[] roomFacilities) {
        // Basic validation for fields that are actually stored
        if (roomNumber == null || roomNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Room number cannot be null or empty.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }

        this.roomNumber = roomNumber.trim();
        this.roomType = roomType;
        this.price = price;
        this.isBooked = false; 
    }



    public String getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPrice() {
        return price;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setRoomNumber(String roomNumber) {
        if (roomNumber != null && !roomNumber.trim().isEmpty()) {
            this.roomNumber = roomNumber.trim();
        } else {
            throw new IllegalArgumentException("Room number cannot be null or empty.");
        }
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }


    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
    }


    public void setBooked(boolean booked) {
        this.isBooked = booked;
    }


    @Override
    public String toString() {
        // Original toString format
        return "Room [roomNumber=" + roomNumber + ", roomType=" + roomType + ", price=" + price + ", isBooked="
                + isBooked + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Room room = (Room) o;
        // Assuming roomNumber is the unique identifier (case-insensitive)
        return roomNumber != null && roomNumber.equalsIgnoreCase(room.roomNumber);
    }

    @Override
    public int hashCode() {
        // Base hash code on the unique identifier (case-insensitive)
        return Objects.hash(roomNumber != null ? roomNumber.toLowerCase() : null);
    }
}