import java.util.Scanner; //import the Scanner class for input

public class KnotToKph {
    public static void main(String[] args) {
        //create a new Scanner object for input
        Scanner input = new Scanner(System.in);

        //prompt user to enter speed in knots
        System.out.print("Skriv in en hastighet i knop:\n");
        double knots = input.nextDouble(); //store user input in variable "knots"

        //convert knots to km/h
        double kmh = knots * 1.852;

        //print out the results
        System.out.println(knots + " knob motsvarar " + kmh + " km/h");
    }
}