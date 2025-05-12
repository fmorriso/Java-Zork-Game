import javax.swing.*;

public class InputUtils
{

    /**
     * Display a question with multiple choices and return the single choice made by the user.
     *
     * @param title    - The title for the pop-up dialog.
     * @param question - The question to ask the user just above the list of available choices.
     * @param choices  - An array of strings representing the choices available.
     * @return - The single choice chosen by the user.
     */
    public static String getSingleChoice(String title, String question, String[] choices)
    {
        String choice = "";
        // force user to choose something by ignoring clicks on the Cancel button
        while(choice.isEmpty())
        {
            JList<String> selections = new JList<>(choices);
            selections.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            Icon icon = UIManager.getIcon("OptionPane.questionIcon");
            Object response = JOptionPane.showInputDialog(null, question, title, JOptionPane.QUESTION_MESSAGE, icon, choices, choices[0]);
            // if the user did not click the Cancel button, capture the response.
            if(response != null) choice = (String)response;
        }
        return choice;
    }

    /** Prompts the user to enter a whole number and returns it.
     * @param title - the title to use when presenting the dialog box to the user.
     * @param msg - helpful text to tell the user what it is they are entering
     * @return - the whole number the user typed in.
     * @implNote - if any non-numeric or numbers with a decimal point are typed into the box, it will pop right back up until
     *             the user enters a valid value.
     */
    public static int getWholeNumber(String title, String msg)
    {
        final int msgType = JOptionPane.INFORMATION_MESSAGE;
        int n = Integer.MIN_VALUE;
        while(n == Integer.MIN_VALUE)
        {
            String resp = JOptionPane.showInputDialog(null, msg, title, msgType);
            try {
                n = Integer.parseInt(resp);
            } catch (Exception e) {
                n = Integer.MIN_VALUE;
            }
        }
        return n;
    }

    /** Prompts the user to enter a decimal number and returns it.
     * @param title - the title to use when presenting the dialog box to the user.
     * @param msg - helpful text to tell the user what it is they are entering
     * @return - the decimal number the user typed in.
     * @implNote - if any non-numeric characters are typed into the box,
     *             it will pop right back up until the user enters a valid value.
     */
    public static double getDecimalNumber(String title, String msg)
    {
        final int msgType = JOptionPane.INFORMATION_MESSAGE;
        double n = Double.MIN_VALUE;
        while(n == Double.MIN_VALUE)
        {
            String resp = JOptionPane.showInputDialog(null, msg, title, msgType);
            try {
                n = Double.parseDouble(resp);
            } catch (Exception e) {
                n = Double.MIN_VALUE;
            }
        }
        return n;
    }


    /** Prompts the user to enter a decimal number within a specified range and returns it.
     * @param title - the title to use when presenting the dialog box to the user.
     * @param min - the minimum value in the range of allowable inputs.
     * @param max - the maximum value in the range of allowable inputs.
     * @return - the whole number the user typed in.
     */
    public static int getWholeNumberInRange(String title, int min, int max)
    {
        final int msgType = JOptionPane.INFORMATION_MESSAGE;
        int n = Integer.MIN_VALUE;
        String msg = String.format("Enter a whole number between %d and %d", min, max);
        while(n < min || n > max)
        {
            String resp = JOptionPane.showInputDialog(null, msg, title, msgType);
            try {
                n = Integer.parseInt(resp);
            } catch (Exception e) {
                n = Integer.MIN_VALUE;
            }
        }


        return n;
    }

    /** Prompts the user to enter a decimal number within a specified range and returns it.
     * @param title - the title to use when presenting the dialog box to the user.
     * @param min - the minimum value in the range of allowable inputs.
     * @param max - the maximum value in the range of allowable inputs.
     * @return - the decimal number the user typed in.
     */
    public static double getDecimalNumberInRange(String title, double min, double max)
    {
        final int msgType = JOptionPane.INFORMATION_MESSAGE;
        double n = Double.MIN_VALUE;
        String msg = String.format("Enter a decimal number between %.1f and %.1f", min, max);
        while(n < min || n > max)
        {
            String resp = JOptionPane.showInputDialog(null, msg, title, msgType);
            try {
                n = Double.parseDouble(resp);
            } catch (Exception e) {
                n = Double.MIN_VALUE;
            }
        }


        return n;
    }


    /** Displays the specified message in a popup window with the specified title
     * @param msg - the message to display
     * @param title - the title of the message dialog
     */
    public static void displayMessage(String msg, String title)
    {
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean askYesNoQuestion(String title, String question, JComponent parent) {

        int result = JOptionPane.showConfirmDialog(
                parent,
                question,
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        return result == JOptionPane.YES_OPTION;
    }

    public static boolean askYesNoQuestion(String title, String question) {
        return askYesNoQuestion(title, question, null);
    }

}
