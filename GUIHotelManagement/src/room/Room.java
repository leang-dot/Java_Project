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
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.isBooked = false;
        this.floor = floor;
        this.viewType = viewType;
        this.hasBalcony = hasBalcony;
        this.roomFacilities = roomFacilities;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
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
        this.price = price;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
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
        this.roomFacilities = roomFacilities;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", roomType='" + roomType + '\'' +
                ", price=" + price +
                ", isBooked=" + isBooked +
                ", floor=" + floor +
                ", viewType='" + viewType + '\'' +
                ", hasBalcony=" + hasBalcony +
                ", roomFacilities=" + Arrays.toString(roomFacilities) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Room room = (Room) o;

        if (Double.compare(room.price, price) != 0)
            return false;
        if (isBooked != room.isBooked)
            return false;
        if (floor != room.floor)
            return false;
        if (hasBalcony != room.hasBalcony)
            return false;
        if (!roomNumber.equals(room.roomNumber))
            return false;
        if (!roomType.equals(room.roomType))
            return false;
        if (!viewType.equals(room.viewType))
            return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(roomFacilities, room.roomFacilities);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = roomNumber.hashCode();
        result = 31 * result + roomType.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (isBooked ? 1 : 0);
        result = 31 * result + floor;
        result = 31 * result + viewType.hashCode();
        result = 31 * result + (hasBalcony ? 1 : 0);
        result = 31 * result + Arrays.hashCode(roomFacilities);
        return result;
    }
}

