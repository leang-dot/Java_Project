package hotelApp;

import user.Admin;

public class hotel {
    
    public static void main(String[] args) {
    
        System.out.println("\n==================== Admin ====================\n");
        // Create an Admin instance
        Admin admin = new Admin("jdoe_admin", "adminsecret123", "John", "Doe", "123-456-7890", "john.doe@hotel.com", "adminpass123");
        
        // Call the Admin login method
        admin.login();
            
        System.out.println("\n==================== Add New Employee ====================\n");
        
        System.out.println("\n==================== Add Room ====================\n");
    
        System.out.println("\n==================== Booking ====================\n");
        
    }
    
}

