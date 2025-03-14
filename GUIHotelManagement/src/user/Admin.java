package user;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Admin extends User{
    // Admin attributes
    private String adminUserName;
    private String adminPassword;
    static List<User> Users = new ArrayList<User>();

    // Admin constructor
    public Admin(String adminUserName, String adminPassword, String firstName, String lastName, String phoneNumber, String email, String password) {
        super(firstName, lastName, phoneNumber, email, password);
        this.adminUserName = adminUserName;
        this.adminPassword = adminPassword;
    }

    

    // Admin login and logout authentication
    public void AdminLogIn(){
        Scanner sc = new Scanner(System.in);
        try  {
            System.out.println("Enter the admin username: ");
            String username = sc.nextLine();
            System.out.println("Enter the admin password: ");
            String password = sc.nextLine();
            if(username.equals(this.adminUserName) && password.equals(this.adminPassword)){
                while (isAdmin) {
                System.out.println("========Admin Menu=======");
                System.out.println("1. Add Account");
                System.out.println("2. Remove Account");
                System.out.println("3. Update Account");
                System.out.println("4. ");
                System.out.println("5. View All Transactions");
                System.out.println("6. View All Payments");
                System.out.println("7. Exit");
                System.out.println("Please choose an option (1-7): ");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        
                        break;
                    case 2:
                        
                        break;
                    case 3:
                        
                        break;
                    case 4:
                        
                        break;
                    case 5:
                        
                        break;
                    case 6:
                        
                        break;
                    case 7:
                        System.out.println("Exiting Admin Menu");
                        return;
                    default:
                        System.out.println("Invalid option");
                        break;
                }
            }
        }else{
                System.out.println("Invalid username or password");
            }
        } catch (Exception e) {
            System.out.println("Error!!!");
        } finally {
            sc.close();
        }
    }

    // Admin methods
    public String getAdminUserName() {
        return adminUserName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void addEmployee(Employee employee) {
    }

    public void removeEmployee(Employee employee) {
    
    }

    public void viewEmployees() {
       
    }

}
