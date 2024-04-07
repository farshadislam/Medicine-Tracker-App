package ca.ucalgary.groupdemo3.groupdemo3;

import ca.ucalgary.groupdemo3.groupdemo3.objects.Medicine;

public class Calculations {
    public static void getRefillPeriod(Medicine medicine) {
        int useDays = 0; // Initializing variable to count number of days left to take medicine before refil

        while (useDays <= 0) { // Repeated input sequence that will not end until a specific type of value is entered
            System.out.println("Enter the number of days that occur in between your dosage intake: "); // Needs to know how often dosage is being taken
            useDays = Integer.parseInt("1"); // Expects an integer that is at least 1
            if (useDays <= 0) {System.out.println("Number of days in between must be at least 1. Try again.");} // Will not allow integers less than 1
        }

        int currentNum = medicine.getCurrentBottle(); // Grabs current number of pills in bottle
        int dosage = medicine.getDosage(); // Grabs medicine's dosage

        int dosesLeft = (int) (double) (currentNum / dosage); // Gets number of doses left until they can no longer medicate with the current amount
        int daysLeft = useDays * dosesLeft; // Multiplies the number of doses by how often it is taken to find

        int numMonths = (int) Math.floor((double) daysLeft / 30);
        daysLeft%=30; // Calculates number of months

        int numWeeks = (int) Math.floor((double) daysLeft / 7);
        daysLeft%=7; // Calculates number of weeks

        int numDays = daysLeft; // Finds number of remaining days past all of that

        System.out.print("Time remaining until next refill: ");
        if (numMonths == 1) {System.out.print(numMonths + " month");}
        else if (numMonths > 1) {System.out.print(numMonths + " months");} // Outputs number of months remaining

        if (numWeeks > 0 && numMonths > 0) {System.out.print(", ");}
        if (numWeeks == 1) {System.out.print(numWeeks + " week");}
        else if (numWeeks > 1) {System.out.print(numWeeks + " weeks");} // Outputs number of weeks remaining

        if (numWeeks > 0 && numDays > 0) {System.out.print(", ");}
        if (numDays == 1) {System.out.print(numDays + " day");}
        else if (numDays > 1 || (numDays == 0 && numWeeks == 0 && numMonths == 0)) {System.out.print(numDays + " days");} // Outputs number of days
        System.out.print(".\n");
    }

    public static void getUnitConversion() {
        // Needs to be written in
    }

    public static void getNextPillTime() {
        // Needs to be written in
    }
}
