package room;

import java.util.Arrays;

public class Room {
    private String roomNumber;
    private String roomType;
    private double price;
    private boolean isBooked;
    private int floor;
    private String viewType;
    private boolean hasBalcony;
    private String[] roomFacilities;

    public Room(String roomNumber, String roomType, double price, int floor, String viewType, boolean hasBalcony,
            String[] roomFacilities) {
        if (roomNumber == null || roomNumber.trim().isEmpty())
            throw new IllegalArgumentException("Room number cannot be null or empty.");
        if (price < 0)
            throw new IllegalArgumentException("Price cannot be negative.");
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.isBooked = false;
        this.floor = floor;
        this.viewType = viewType;
        this.hasBalcony = hasBalcony;
        this.roomFacilities = roomFacilities != null ? roomFacilities : new String[0];
    }

    // Getters and Setters
    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        if (roomNumber != null && !roomNumber.trim().isEmpty())
            this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price >= 0)
            this.price = price;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        this.isBooked = booked;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public boolean isHasBalcony() {
        return hasBalcony;
    }

    public void setHasBalcony(boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }

    public String[] getRoomFacilities() {
        return roomFacilities;
    }

    public void setRoomFacilities(String[] roomFacilities) {
        this.roomFacilities = roomFacilities != null ? roomFacilities : new String[0];
    }

    // Utility Methods
    public void addFacility(String facility) {
        if (facility != null && !facility.trim().isEmpty() && !Arrays.asList(roomFacilities).contains(facility)) {
            String[] newFacilities = Arrays.copyOf(roomFacilities, roomFacilities.length + 1);
            newFacilities[roomFacilities.length] = facility;
            roomFacilities = newFacilities;
        }
    }

    public void removeFacility(String facility) {
        if (facility != null) {
            int index = Arrays.asList(roomFacilities).indexOf(facility);
            if (index >= 0) {
                String[] newFacilities = new String[roomFacilities.length - 1];
                System.arraycopy(roomFacilities, 0, newFacilities, 0, index);
                System.arraycopy(roomFacilities, index + 1, newFacilities, index, roomFacilities.length - index - 1);
                roomFacilities = newFacilities;
            }
        }
    }

    public void resetBookingStatus() {
        isBooked = false;
        System.out.println("Room " + roomNumber + " booking status reset.");
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + roomType + ") - $" + price + " [Floor: " + floor + ", View: " + viewType +
                ", Balcony: " + hasBalcony + ", Booked: " + isBooked + ", Facilities: "
                + Arrays.toString(roomFacilities) + "]";
    }
}