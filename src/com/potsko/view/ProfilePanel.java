public class ProfilePanel extends JPanel {
    public ProfilePanel(MainFrame mainFrame, int userId) {
        // Debug print to check what userId is being passed
        System.out.println("ProfilePanel userId: " + userId);

        setLayout(new BorderLayout());
        setBackground(new Color(245, 246, 240));
        // ...rest of your constructor...
    }
    // ...existing code...
}