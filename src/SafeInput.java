import java.util.Scanner;

public class SafeInput {

    /**
     * Part A: Prompts the user until a non-zero length string is entered.
     *
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a String response that is not zero length
     */
    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String retString = "";
        do {
            System.out.print("\n" + prompt + ": ");
            retString = pipe.nextLine();
        } while (retString.length() == 0);

        return retString;
    }

    /**
     * Part B: Prompts the user until they enter a valid integer.
     *
     * @param pipe   A Scanner opened to read from System.in
     * @param prompt The prompt for the user
     * @return A valid integer value
     */
    public static int getInt(Scanner pipe, String prompt) {
        int retVal = 0;
        String trash = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextInt()) {
                retVal = pipe.nextInt();
                pipe.nextLine();
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("Invalid input. Please enter an integer. You entered: " + trash);
            }
        } while (!done);

        return retVal;
    }

    /**
     * Part C: Prompts the user until they enter a valid double.
     *
     * @param pipe   A Scanner opened to read from System.in
     * @param prompt The prompt for the user
     * @return A valid double value
     */
    public static double getDouble(Scanner pipe, String prompt) {
        double retVal = 0;
        String trash = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextDouble()) {
                retVal = pipe.nextDouble();
                pipe.nextLine();
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("Invalid input. Please enter a double. You entered: " + trash);
            }
        } while (!done);

        return retVal;
    }

    /**
     * Part D: Prompts the user for an integer within an inclusive range.
     *
     * @param pipe   A Scanner opened to read from System.in
     * @param prompt The prompt for the user (should not include range)
     * @param low    The low bound of the inclusive range
     * @param high   The high bound of the inclusive range
     * @return A valid integer within the specified range
     */
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int retVal = 0;
        String trash = "";
        boolean done = false;

        do {
            // Display the prompt with the range
            System.out.print("\n" + prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextInt()) {
                retVal = pipe.nextInt();
                pipe.nextLine();

                if (retVal >= low && retVal <= high) {
                    done = true;
                } else {
                    System.out.println("Input is out of range. Must be between " + low + " and " + high + ".");
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("Invalid input. Please enter an integer. You entered: " + trash);
            }
        } while (!done);

        return retVal;
    }

    /**
     * Part E: Prompts the user for a double within an inclusive range.
     *
     * @param pipe   A Scanner opened to read from System.in
     * @param prompt The prompt for the user (should not include range)
     * @param low    The low bound of the inclusive range
     * @param high   The high bound of the inclusive range
     * @return A valid double within the specified range
     */
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double retVal = 0;
        String trash = "";
        boolean done = false;

        do {
            // Display the prompt with the range
            System.out.print("\n" + prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextDouble()) {
                retVal = pipe.nextDouble();
                pipe.nextLine();

                if (retVal >= low && retVal <= high) {
                    done = true;
                } else {
                    System.out.println("Input is out of range. Must be between " + low + " and " + high + ".");
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("Invalid input. Please enter a double. You entered: " + trash);
            }
        } while (!done);

        return retVal;
    }

    /**
     * Part F: Prompts the user for a Yes or No response [Y/N].
     *
     * @param pipe   A Scanner opened to read from System.in
     * @param prompt The prompt for the user
     * @return true for 'Y' or 'y', false for 'N' or 'n'
     */
    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        String response = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + " [Y/N]: ");
            response = pipe.nextLine();
            if (response.equalsIgnoreCase("Y")) {
                done = true;
                return true;
            } else if (response.equalsIgnoreCase("N")) {
                done = true;
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'Y' or 'N'.");
            }
        } while (!done);

        return false;
    }
    /**
     * Part G: Prompts the user for a String that matches a RegEx pattern.
     *
     * @param pipe   A Scanner opened to read from System.in
     * @param prompt The prompt for the user
     * @param regEx  The Regular Expression pattern to match
     * @return A String that matches the RegEx pattern
     */
    public static String getRegExString(Scanner pipe, String prompt, String regEx) {
        String response = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + ": ");
            response = pipe.nextLine();
            if (response.matches(regEx)) {
                done = true;
            } else {
                System.out.println("Invalid input. Does not match the required pattern.");
            }
        } while (!done);

        return response;
    }

    /**
     * Part H: Prints a 60-character-wide header with a centered message.
     *
     * @param msg The message to be centered in the header
     */
    public static void prettyHeader(String msg) {
        int totalWidth = 60;
        int msgLength = msg.length();
        int innerWidth = totalWidth - 6;
        int leftSpaces = (innerWidth - msgLength) / 2;
        int rightSpaces = innerWidth - msgLength - leftSpaces;

        // Line 1: All stars
        for (int i = 0; i < totalWidth; i++) {
            System.out.print("*");
        }
        System.out.println();

        // Line 2: Message line
        System.out.print("***");
        for (int i = 0; i < leftSpaces; i++) {
            System.out.print(" ");
        }
        System.out.print(msg);
        for (int i = 0; i < rightSpaces; i++) {
            System.out.print(" ");
        }
        System.out.println("***");

        // Line 3: All stars
        for (int i = 0; i < totalWidth; i++) {
            System.out.print("*");
        }
        System.out.println();
    }
}