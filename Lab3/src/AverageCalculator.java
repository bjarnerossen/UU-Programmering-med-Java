import java.util.Scanner;
import java.util.HashMap;

/**
 * Laboration 3 - UU - Inledande programmering med Java (VT2023)
 * Skriv ett program som med hjälp av kommandofönstret tar in ett antal heltal
 * för att sedan skriva ut medelvärdet och hur många av de angivna talen som
 * endast angetts en gång. Programmet ska även kunna hantera felaktig input.
 */
public class AverageCalculator {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("** HELTALSHANTERARE **");

        // Prompt user for the number of ints
        int n = getNumberOfIntegers(input);

        // create an empty array to store the numbers provided by the user
        int[] numbers = new int[n];

        // Ask the user for each number and store it in the array
        for (int i = 0; i < n; i++) {
            numbers[i] = getInteger(input, i + 1);
        }

        // Calculate and print out the average and number of unique numbers
        int uniqueNumbers = countUniqueNumbers(numbers);
        int average = calculateAverage(numbers);
        System.out.println("Du angav " + n + " tal.");
        System.out.println("Varav " + uniqueNumbers + " av talen bara angavs en gång.");
        System.out.println("Medelvärdet för talen är " + average + " .");
    }

    /**
     * Prompt the user for the number of integers to enter.
     */
    public static int getNumberOfIntegers(Scanner input) {
        int n = 0;
        boolean validNum = false;

        while (!validNum) {
            System.out.print("Hur många heltal vill du ange: ");
            if (input.hasNextInt()) {
                n = input.nextInt();
                if (n > 0) {
                    validNum = true;
                } else {
                    System.out.println("Antalet heltal du anger måste vara ett positivt heltal.");
                }
            } else {
                System.out.println("Ogiltigt värde.");
                input.next();
            }
        }
        return n;
    }

    /**
     * Prompt the user for an integer.
     */

    public static int getInteger(Scanner input, int index) {
        int num = 0;
        boolean validNum = false;

        while (!validNum) {
            System.out.print("Ange heltal " + index + " : ");
            if (input.hasNextInt()) {
                num = input.nextInt();
                validNum = true;
            } else {
                System.out.println("Ogiltigt värde.");
                input.next();
            }
        }
        return num;
    }

    /**
     * Count the number of unique integers in the array.
     */
    public static int countUniqueNumbers(int[] numbers) {
        int uniqueNumbers = 0;

        // Create a HashMap to keep track of the frequency of each number
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < numbers.length; i++) {
            if (!map.containsKey(numbers[i])) {
                map.put(numbers[i], 1);
            } else {
                int count = map.get(numbers[i]);
                map.put(numbers[i], count + 1);
            }
        }

        // Count the numbers that only occur once
        for (Integer number : map.keySet()) {
            if (map.get(number) == 1) {
                uniqueNumbers++;
            }
        }

        return uniqueNumbers;
    }


    /**
     * Calculate the average of the integers in the array.
     */
    public static int calculateAverage(int[] numbers) {
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
        return sum / numbers.length;
    }
}
