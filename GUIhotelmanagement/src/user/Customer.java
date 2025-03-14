package user;

import booking.Booking;
import java.util.ArrayList;
import java.util.List;
public class Customer extends User {
    private static int customerCount = 0;
    private int customerID;
    static List<Booking> Bookings = new ArrayList<Booking>();
    
    public Customer(int customerID,String firstName, String lastName, String phoneNumber, String email, String password) {
        super(firstName, lastName, phoneNumber, email, password);
        this.customerID = customerID;
        customerCount++;
    }

    public void AdminLogIn() {
        System.out.println("Customer log in");
    }

    public static int getCustomerCount() {
        return customerCount;
    }

    public int getCustomerID() {
        return customerID;
    }
    
}
