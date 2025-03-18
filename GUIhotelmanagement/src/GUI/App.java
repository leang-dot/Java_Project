package GUI;
import javax.swing.*;

public class App extends JFrame {
    public App() {
        setTitle("Hotel Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        // Create a JPanel and set it as the content pane
        JPanel panel = new JPanel();
        setContentPane(panel);
        
        setVisible(false);
    }

    public static void main(String[] args) {
        
        new App().setVisible(true);
    }
}