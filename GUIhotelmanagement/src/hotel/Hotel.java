package hotel;

import room.Room;
import booking.Booking;
import user.Customer;
import user.Employee;
import java.util.ArrayList;
import java.util.List;

public class Hotel extends HotelEntity {
    private String name;
    private String location;
    private List<Room> rooms;
    private List<Booking> bookings;
    private List<Employee> employees;

    public Hotel(String id, String status, String name, String location) {
        super(id, status);
        this.name = name;
        this.location = location;
        this.rooms = new ArrayList<>();
        this.bookings = new ArrayList<>();
        this.employees = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty())
        this.name = name;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }

    public List<Room> getRooms() {
        return new ArrayList<>(rooms);
    }
    
    public List<Booking> getBookings() {
        return new ArrayList<>(bookings);
    }
    
    public List<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }
    
    // Room Management
    public void addRoom(Room room) {
        if (!isActive())
            throw new IllegalStateException("Hotel is inactive.");
            if (room != null && findRoomByNumber(room.getRoomNumber()) == null) {
                rooms.add(room);
                System.out.println("Room " + room.getRoomNumber() + " added to " + name);
            } else {
                System.out.println(
                    "Room " + (room != null ? room.getRoomNumber() : "null") + " already exists or is invalid.");
        }
    }
    
    public void removeRoom(String roomNumber) {
        if (!isActive())
        throw new IllegalStateException("Hotel is inactive.");
        Room room = findRoomByNumber(roomNumber);
        if (room != null && rooms.remove(room)) {
            System.out.println("Room " + roomNumber + " removed from " + name);
        } else {
            System.out.println("Room " + roomNumber + " not found.");
        }
    }

    public Room findRoomByNumber(String roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber().equals(roomNumber))
                return room;
        }
        return null;
    }

    public List<Room> getAvailableRooms() {
        List<Room> available = new ArrayList<>();
        for (Room room : rooms) {
            if (!room.isBooked())
                available.add(room);
        }
        return available;
    }
    
    // Booking Management
    public void addBooking(Booking booking, Customer customer) {
        if (!isActive())
            throw new IllegalStateException("Hotel is inactive.");
            if (booking != null && !bookings.contains(booking) && customer != null) {
                Room room = findRoomByNumber(booking.getRoomNumber());
            if (room != null && !room.isBooked()) {
                bookings.add(booking);
                customer.addBooking(booking); 
                room.setBooked(true);
                System.out.println(
                        "Booking " + booking.getBookingId() + " added to " + name + " for " + customer.getFirstName());
            } else {
                System.out.println("Room " + booking.getRoomNumber() + " is unavailable or not found.");
            }
        }
    }

    public void removeBooking(String bookingId, Customer customer) {
        if (!isActive())
            throw new IllegalStateException("Hotel is inactive.");
            Booking booking = findBookingById(bookingId);
            if (booking != null && bookings.remove(booking)) {
                Room room = findRoomByNumber(booking.getRoomNumber());
            if (room != null)
            room.setBooked(false);
            customer.removeBooking(booking); // Use Customer instance method instead of direct access
            System.out.println("Booking " + bookingId + " removed from " + name);
        } else {
            System.out.println("Booking " + bookingId + " not found.");
        }
    }
    
    public Booking findBookingById(String bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId().equals(bookingId))
                return booking;
        }
        return null;
    }

    // Employee Management
    public void addEmployee(Employee employee) {
        if (!isActive())
            throw new IllegalStateException("Hotel is inactive.");
        if (employee != null && findEmployeeById(employee.getEmployeeID()) == null) {
            employees.add(employee);
            System.out.println("Employee " + employee.getFirstName() + " added to " + name);
        } else {
            System.out.println("Employee already exists or is invalid.");
        }
    }
    
    public void removeEmployee(int employeeId) {
        if (!isActive())
            throw new IllegalStateException("Hotel is inactive.");
        Employee employee = findEmployeeById(employeeId);
        if (employee != null && employees.remove(employee)) {
            System.out.println("Employee " + employee.getFirstName() + " removed from " + name);
        } else {
            System.out.println("Employee " + employeeId + " not found.");
        }
    }

    public Employee findEmployeeById(int employeeId) {
        for (Employee employee : employees) {
            if (employee.getEmployeeID() == employeeId)
            return employee;
        }
        return null;
    }
    
    // Display Functions
    public void displayRooms() {
        if (rooms.isEmpty())
        System.out.println("No rooms in " + name + ".");
        else {
            System.out.println("Rooms in " + name + " (" + status + "):");
            for (Room room : rooms)
            System.out.println(room);
        }
    }
    
    public void displayBookings() {
        if (bookings.isEmpty())
            System.out.println("No bookings in " + name + ".");
        else {
            System.out.println("Bookings in " + name + " (" + status + "):");
            for (Booking booking : bookings)
                System.out.println(booking);
            }
        }
        
        public void displayAvailableRooms() {
            List<Room> available = getAvailableRooms();
            if (available.isEmpty())
            System.out.println("No available rooms in " + name + ".");
            else {
                System.out.println("Available rooms in " + name + ":");
            for (Room room : available)
            System.out.println(room);
        }
    }
    
    public void displayEmployees() {
        if (employees.isEmpty())
            System.out.println("No employees in " + name + ".");
        else {
            System.out.println("Employees in " + name + ":");
            for (Employee employee : employees)
            System.out.println(employee);
        }
    }

    // IManageable Implementations
    @Override
    public void add() {
        if (isActive())
            System.out.println("Hotel " + name + " is already active.");
        else {
            setStatus("Active");
            System.out.println("Hotel " + name + " activated (ID: " + id + ").");
        }
    }
    
    @Override
    public void update() {
        if (!isActive())
            throw new IllegalStateException("Hotel is inactive.");
            System.out.println("Hotel " + name + " updated. Location: " + location);
        }
        
    @Override
    public void delete() {
        if (!isActive())
        System.out.println("Hotel " + name + " is already inactive.");
        else {
            setStatus("Inactive");
            rooms.clear();
            bookings.clear();
            employees.clear();
            System.out.println("Hotel " + name + " deactivated (ID: " + id + ").");
        }
    }

    @Override
    public String toString() {
        return "Hotel: " + name + " (ID: " + id + ") at " + location + " [Status: " + status + "]";
    }
}
    // if (name == null || name.trim().isEmpty())
    //     throw new IllegalArgumentException("Hotel name cannot be null or empty.");