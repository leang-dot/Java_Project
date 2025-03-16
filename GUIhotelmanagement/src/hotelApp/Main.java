// package hotelApp;

// import user.Admin;

// public class Main {

//     public static void main(String[] args) {

//         System.out.println("\n==================== Admin ====================\n");
//         // Create an Admin instance
//         Admin admin = new Admin("jdoe_admin", "adminsecret123", "John", "Doe", "123-456-7890", "john.doe@hotel.com", "adminpass123");

//         // Call the Admin login method
//         admin.login();

//         System.out.println("\n==================== Add New Employee ====================\n");

//         System.out.println("\n==================== Add Room ====================\n");

//         System.out.println("\n==================== Booking ====================\n");

//     }

// }
package hotelApp;

import hotel.Hotel;
import room.Room;
import booking.Booking;
import user.Admin;
import user.Customer;
import user.Employee;
import transaction.Payment;

public class Main {
    public static void main(String[] args) {
        // Create a hotel
        Hotel hotel = new Hotel("H001", "Inactive", "Grand Hotel", "123 Main St");
        hotel.add();

        // Create an admin
        Admin admin = new Admin("admin", "admin123", "Jane", "Smith", "555-1234", "jane@example.com", "pass123");
        if (admin.login()) {
            System.out.println("Admin logged in.");
        }

        // Add an employee
        Employee employee = new Employee(1, "Receptionist", 3000.0, "456 Elm St", "1990-01-01", "2023-01-01", "Active",
                "9-5",
                "Bob", "Jones", "555-5678", "bob@example.com", "pass456");
        admin.addEmployee(employee);
        hotel.addEmployee(employee);

        // Add rooms
        String[] facilities = { "WiFi", "TV" };
        Room room1 = new Room("101", "Single", 50.0, 1, "City", false, facilities);
        Room room2 = new Room("102", "Double", 80.0, 1, "Sea", true, facilities);
        hotel.addRoom(room1);
        hotel.addRoom(room2);

        // Create a customer
        Customer customer = new Customer(1, "John", "Doe", "123-456-7890", "john@example.com", "pass123");
        if (customer.login()) {
            System.out.println("Customer logged in.");
        }

        // Create a booking
        Booking booking = new Booking("2025-03-16", "2025-03-17", "2025-03-19", "101", "Single", 50.0, 1, "City", false,
                facilities,
                100.0, "John Doe", "123-456-7890", "john@example.com", "New York", "USA");
        hotel.addBooking(booking, customer);

        // Make a payment
        Payment payment = new Payment(1, 100.0, "Credit Card", "John", "Doe", "123-456-7890", "john@example.com",
                "pass123");
        payment.makePayment();

        // Display everything
        hotel.displayRooms();
        hotel.displayBookings();
        hotel.displayEmployees();
        customer.viewBookings();
        admin.viewEmployees();

        // Remove the booking
        hotel.removeBooking(booking.getBookingId(), customer);
        hotel.displayBookings();
        customer.viewBookings();
    }
}
