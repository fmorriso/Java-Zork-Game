import javax.swing.JOptionPane;

public class OutputUtils
{
    /**
     * Displays the specified message in a popup window with the specified title
     *
     * @param msg   - the message to display
     * @param title - the title of the message dialog
     */
    public static void displayMessage(String msg, String title)
    {
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
