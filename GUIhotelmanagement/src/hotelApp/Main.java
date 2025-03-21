package hotelApp;

import user.Admin;
import user.Employee;
import user.Customer;
// import booking.Booking;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin = null;

        // Initial admin setup
        System.out.println("=== Admin Setup ===");
        System.out.print("Enter admin username: ");
        String adminUsername = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String adminPassword = scanner.nextLine();
        System.out.print("Enter admin first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter admin last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter admin phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter admin email: ");
        String email = scanner.nextLine();
        System.out.print("Enter admin password (for login): ");
        String password = scanner.nextLine();

        try {
            admin = new Admin(adminUsername, adminPassword, firstName, lastName, phoneNumber, email, password);
            System.out.println("Admin created: " + admin);
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating admin: " + e.getMessage());
            scanner.close();
            return;
        }

        // Test Admin login

        while (true) {
            System.out.println("\n=== Hotel Management System ===");
            System.out.println("1. Add Employee");
            System.out.println("2. Add Customer");
            System.out.println("3. View All Users");
            System.out.println("4. Update Employee");
            System.out.println("5. Update Customer");
            System.out.println("6. Add Booking for Customer");
            System.out.println("7. Check-In");
            System.out.println("8. Check-Out");
            System.out.println("9. Remove Employee");
            System.out.println("10. Remove Customer");
            System.out.println("11. Exit");
            System.out.print("Enter your choice (1-11): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number between 1 and 11.");
                continue;
            }

            switch (choice) {
                case 1: // Add Employee
                    System.out.print("Enter employee ID: ");
                    int empId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter employee role: ");
                    String role = scanner.nextLine();
                    System.out.print("Enter salary: ");
                    double salary = Double.parseDouble(scanner.nextLine());
                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter date of birth (YYYY-MM-DD): ");
                    String dob = scanner.nextLine();
                    System.out.print("Enter hire date (YYYY-MM-DD): ");
                    String hireDate = scanner.nextLine();
                    System.out.print("Enter work status: ");
                    String workStatus = scanner.nextLine();
                    System.out.print("Enter work schedule: ");
                    String workSchedule = scanner.nextLine();
                    System.out.print("Enter first name: ");
                    String empFirstName = scanner.nextLine();
                    System.out.print("Enter last name: ");
                    String empLastName = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String empPhone = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String empEmail = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String empPassword = scanner.nextLine();

                    Employee employee = new Employee(empId, role, salary, address, dob, hireDate, workStatus,
                            workSchedule,
                            empFirstName, empLastName, empPhone, empEmail, empPassword);
                    admin.addEmployee(employee);
                    System.out.println("Added employee: " + employee);
                    break;

                case 2: // Add Customer
                    System.out.print("Enter customer ID: ");
                    int custId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter first name: ");
                    String custFirstName = scanner.nextLine();
                    System.out.print("Enter last name: ");
                    String custLastName = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String custPhone = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String custEmail = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String custPassword = scanner.nextLine();

                    Customer customer = new Customer(custId, custFirstName, custLastName, custPhone, custEmail,
                            custPassword);
                    admin.addCustomer(customer);
                    System.out.println("Added customer: " + customer);
                    break;

                case 3: // View All Users
                    admin.viewAllUsers();
                    break;

                case 4: // Update Employee
                    System.out.print("Enter employee ID to update: ");
                    int empIdToUpdate = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter new first name: ");
                    String newEmpFirstName = scanner.nextLine();
                    System.out.print("Enter new last name: ");
                    String newEmpLastName = scanner.nextLine();
                    System.out.print("Enter new phone number: ");
                    String newEmpPhone = scanner.nextLine();
                    System.out.print("Enter new email: ");
                    String newEmpEmail = scanner.nextLine();

                    admin.updateEmployee(empIdToUpdate, newEmpFirstName, newEmpLastName, newEmpPhone, newEmpEmail);
                    System.out.println("Updated employee details:");
                    admin.viewAllUsers();
                    break;

                case 5: // Update Customer
                    System.out.print("Enter customer ID to update: ");
                    int custIdToUpdate = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter new first name: ");
                    String newCustFirstName = scanner.nextLine();
                    System.out.print("Enter new last name: ");
                    String newCustLastName = scanner.nextLine();
                    System.out.print("Enter new phone number: ");
                    String newCustPhone = scanner.nextLine();
                    System.out.print("Enter new email: ");
                    String newCustEmail = scanner.nextLine();

                    admin.updateCustomer(custIdToUpdate, newCustFirstName, newCustLastName, newCustPhone, newCustEmail);
                    System.out.println("Updated customer details:");
                    admin.viewAllUsers();
                    break;

                // case 6: // Add Booking for Customer
                // System.out.print("Enter customer ID: ");
                // int custIdForBooking = Integer.parseInt(scanner.nextLine());
                // System.out.print("Enter booking ID: ");
                // String bookingId = scanner.nextLine();
                // System.out.print("Enter room number: ");
                // int roomNumber = Integer.parseInt(scanner.nextLine());
                // System.out.print("Enter initial status (e.g., reserved): ");
                // String status = scanner.nextLine();

                // User user = admin.findUserById(custIdForBooking, "Customer");
                // if (user instanceof Customer) {
                // Booking booking = new Booking();
                // ((Customer) user).addBooking(booking);
                // System.out.println("Added booking: " + booking);
                // } else {
                // System.out.println("Customer not found!");
                // }
                // break;

                case 7: // Check-In
                    System.out.print("Enter booking ID: ");
                    String checkInBookingId = scanner.nextLine();
                    System.out.print("Enter room number: ");
                    int checkInRoomNumber = Integer.parseInt(scanner.nextLine());
                    admin.checkIn(checkInBookingId, checkInRoomNumber);
                    admin.viewAllUsers();
                    break;

                case 8: // Check-Out
                    System.out.print("Enter booking ID: ");
                    String checkOutBookingId = scanner.nextLine();
                    System.out.print("Enter room number: ");
                    int checkOutRoomNumber = Integer.parseInt(scanner.nextLine());
                    admin.checkOut(checkOutBookingId, checkOutRoomNumber);
                    admin.viewAllUsers();
                    break;

                case 9: // Remove Employee
                    System.out.print("Enter employee ID to remove: ");
                    int empIdToRemove = Integer.parseInt(scanner.nextLine());
                    admin.removeEmployee(empIdToRemove);
                    System.out.println("After removing employee:");
                    admin.viewAllUsers();
                    break;

                case 10: // Remove Customer
                    System.out.print("Enter customer ID to remove: ");
                    int custIdToRemove = Integer.parseInt(scanner.nextLine());
                    admin.removeCustomer(custIdToRemove);
                    System.out.println("After removing customer:");
                    admin.viewAllUsers();
                    break;

                case 11: // Exit
                    System.out.println("Exiting program...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 11.");
            }
        }
    }
}