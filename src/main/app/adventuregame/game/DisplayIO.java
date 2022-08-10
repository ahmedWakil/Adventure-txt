package adventuregame.game;

import java.util.List;
import java.util.Scanner;

public class DisplayIO {

    private static final int CHARACTER_WIDTH = 75;
    private static final Scanner scanner = new Scanner(System.in);


    public static void display(String message) {
        if (message.length() > CHARACTER_WIDTH) {
            String toDisplay = truncateStringHorizontally(message);
            System.out.println(toDisplay);
        } else
            System.out.println(message);

    }

    private static String truncateStringHorizontally(String input) {
        StringBuilder stringBuilder = new StringBuilder(input);

        int current = CHARACTER_WIDTH;
        boolean hasNext = stringBuilder.length() > CHARACTER_WIDTH;
        while(hasNext) {
            boolean truncatedLine = false;
            int truncateIndex = current;
            while(!truncatedLine) {
                if (stringBuilder.charAt(truncateIndex) == ' ') {
                    stringBuilder.replace(truncateIndex, truncateIndex + 1, "\n");
                    truncatedLine = true;
                }

                truncateIndex--;
            }

            if (current + CHARACTER_WIDTH <= stringBuilder.length())
                current += CHARACTER_WIDTH;
            else
                hasNext = false;
        }

        return stringBuilder.toString();
    }

    private static String orderedListOptionsAsString(List<String> listOfOptions) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < listOfOptions.size(); i++) {
            stringBuilder.append(i + 1).append(". ").append(listOfOptions.get(i)).append("\n");
        }

        return stringBuilder.toString();
    }

    public static int getUserSelectionFromList(List<String> options) {
        String stringOptions = orderedListOptionsAsString(options);
        display(stringOptions);

        boolean validSelection = false;
        int intValue = 0;
        while (!validSelection) {
            String input = scanner.nextLine();
            if (isNumeric(input)) {
                intValue = Integer.parseInt(input) - 1;
                if (0 <= intValue && intValue < options.size())
                    validSelection = true;
            }

            if (!validSelection)
                display("Please enter the integer that corresponds to the option you are trying to select.");
        }

        return intValue;
    }

    public static String getUserCommand() {
        String input = scanner.nextLine();
        return input;
    }

    private static boolean isNumeric(String string) {
        if (string == null || string.length() == 0)
            return false;

        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
