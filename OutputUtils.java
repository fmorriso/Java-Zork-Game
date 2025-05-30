import javax.swing.JOptionPane;

public class OutputUtils
{
    /**
     * Displays the specified message in a popup window with the specified title.
     * It is assumed that the message type is informational.
     *
     * @param msg   - the message to display
     * @param title - the title of the message dialog
     */
    public static void displayMessage(String msg, String title)
    {
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Displays the specified information message in a popup window with the specified title
     *
     * @param msg   - the message to display
     * @param title - the title of the message dialog
     */
    public static void displayInformation(String msg, String title)
    {
        displayMessage(msg, title);
    }

    public static void displayWarning(String msg, String title)
    {
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.WARNING_MESSAGE);
    }

    public static void displayError(String msg, String title)
    {
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.ERROR_MESSAGE);
    }
}
