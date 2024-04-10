package ca.ucalgary.groupdemo3.groupdemo3;

import ca.ucalgary.groupdemo3.groupdemo3.objects.FoodIntake;
import ca.ucalgary.groupdemo3.groupdemo3.objects.Medicine;
import ca.ucalgary.groupdemo3.groupdemo3.objects.SideEffects;
import ca.ucalgary.groupdemo3.groupdemo3.util.FileLoader;
import ca.ucalgary.groupdemo3.groupdemo3.util.FileSaver;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.DecimalFormat;
/**
 * Data tracking for user inputted medications with respective attributes
 * Menu class for input/output to System by typing into the terminal
 * @authors Farshad Islam, Hira Asad, Harris Jan
 * @emails (respectively) farshad.islam@ucalgary.ca, hira.asad@ucalgary.ca, harris.jan@ucalgary.ca
 * @version 1.0
 */

public class Menu { // commenttt

    private static Data data = new Data();
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<String> menuOptions = new ArrayList<>();
    static {
        menuOptions.add("Exit");
        menuOptions.add("Enter new medicine's information"); // Harris DONE: includes, dosages, full bottle amount, current bottle amount
        menuOptions.add("Possible side effects of specific medication"); // Hira (should ask for medication name first and then list out its side effects)
        menuOptions.add("Food intake"); // Hira ((should ask for medication name first and then list out food/water intake instructions)
        menuOptions.add("View specific medication information"); // Harris
        menuOptions.add("View all medications information");// Harris
        menuOptions.add("View all medication side effect infromation");
        menuOptions.add("View all medication food intake infromation");
        menuOptions.add("Refilling period for a specific medication."); // Farshad (Refill period time calculation in months, weeks, and days for example 1 month 2 weeks and 5 days)
        menuOptions.add("Expense of all medications"); // Farshad (Basically try to loop through hashmap and add all the values together) an idea I could give is that you also create another menu option where a specific number of medications price could be calculated instead of just 1.
        menuOptions.add("Delete medication");
        menuOptions.add("Save");
        menuOptions.add("Load");

    }

    private static String optMessage = """
            Store and access medicines for patient's medicinal usage.
            \tMenu Options
            """;
    static {
        StringBuilder sb = new StringBuilder();
        sb.append(optMessage);
        for (int i = 0; i < menuOptions.size(); i++) {
            sb.append(String.format("\t%d. %s\n", i, menuOptions.get(i)));
        }
        optMessage = sb.toString();
    }

    private static final String MEDICINE_FORMAT = "%-20s %-20s %-20s %-20s";
    private static final String INFORMATION_FORMAT = "%-20s %-20s";
    private static final String MEDICINE_HEADER = String.format(MEDICINE_FORMAT, "Name", "Dosage per day", "Full Bottle", "Currently in Bottle");
    private static final String SIDE_EFFECT_HEADER = String.format(INFORMATION_FORMAT, "Name", "Side Effects");
    private static final String FOOD_INTAKE_HEADER = String.format(INFORMATION_FORMAT, "Name", "Food Intake");

    private static String MEDICINE_SEPERATOR = "";

    static {
        for (int i = 0; i < MEDICINE_HEADER.length(); i++) {
            MEDICINE_SEPERATOR += "-";
        }
    }

    private static String getAnyOption(){
        String option;
        do {
            option = scanner.nextLine().trim();
        }while(option.isEmpty());
        return option;
    }
    public static void menuLoop() {
        System.out.println(optMessage);
        String userChoice = getAnyOption();
        int selectedOption = Integer.parseInt(userChoice);
        while (selectedOption != 0) {
            if (selectedOption > 0 && selectedOption < menuOptions.size()) {
                System.out.printf("Selected option %d. %s%n", selectedOption, menuOptions.get(selectedOption));
                System.out.println("Press any key to continue...");
                scanner.nextLine();
            }
            switch (selectedOption) {
                case 1 -> menuEnterNewMedicine();
                case 2 -> menuEnterSideEffects();
                case 3 -> menuEnterFoodIntake();
                case 4 -> menuEnterViewMedication();
                case 5 -> menuEnterViewMedications();
                case 6 -> menuViewMedicationsSideEffects();
                case 7 -> menuViewMedicationsFoodIntake();
                case 8 -> menuEnterStatisticsOfRefillingPeriod();
                case 9 -> menuEnterTotalExpense();
                case 10 -> menuDeleteMedication();
                case 11 -> menuSave();
                case 12 -> menuLoad();
                default -> System.out.printf("Option %d not recognizable!%n", selectedOption);
            }
            System.out.println("Press any key to continue...");
            scanner.nextLine();
            System.out.println(optMessage);
            userChoice = getAnyOption();
            selectedOption = Integer.parseInt(userChoice);//
        }
        System.out.println("Thanks for using the medicine tracker program. Bye!");
    }

    private static void menuLoad() {
        String filename = "";
        File file;
        //checks to see if there has been a valid file name that was entered
        // if the file name was not valid, function continues to ask for a file name
        while (filename.isEmpty() || (!((file = new File(filename)).exists() || file.canRead()))) {
            System.out.println("Enter a filename: ");
            filename = scanner.nextLine().trim();
        }
        // loads the file
        Data data = FileLoader.load(file);
        if (data == null){
            System.out.printf("Faled to load data from %s%n", filename); //output if file was not loaded
        }
        else{
            System.out.printf("Data loaded from %s%n", filename); //output for if file was loaded
            Menu.data = data;
        }
    }

    private static void menuSave() {
        String filename = "";
        File file;
        // checks to see if the filename was valid, otherwise it will continue to ask for a file name
        while (filename.isEmpty() || ((file = new File(filename)).exists() && !file.canWrite())) {
            System.out.println("Enter a filename: ");
            filename = scanner.nextLine().trim();
        }
        //checks to see if the file was saved
        if (FileSaver.save(file, data)){
            System.out.printf("Saved to %s%n", filename); //output for if file has been saved
        } else{
            System.err.printf("Failed to save to %s%n", filename); //output if file has not been saved
        }
    }



    private static void menuViewMedicationsFoodIntake() { //Allows the user to view all information on food intake.
        System.out.println(FOOD_INTAKE_HEADER);
        System.out.println(MEDICINE_SEPERATOR);
        for (FoodIntake food_intake: data.getAllFoodIntakeInfo()){ //Loop to access all medicine information
            System.out.printf(INFORMATION_FORMAT, food_intake.getName(), food_intake.getFoodIntake()); //Formats food intake information and prints it for the user
            System.out.println(); //Added line for better formatting.
        }
    }

    private static void menuViewMedicationsSideEffects() { //Allows the user to view all information on side effects.
        System.out.println(SIDE_EFFECT_HEADER);
        System.out.println(MEDICINE_SEPERATOR);
        for (SideEffects side_effects: data.getAllSideEffectInfo()){ //Loop to access all medicine information.
            System.out.printf(INFORMATION_FORMAT, side_effects.getName(), side_effects.getSideEffects()); //Formats side effect information and prints it to the user
            System.out.println(); //Added line for better formatting.
        }
    }

    private static void menuEnterNewMedicine() {
        boolean successName;
        do {
            String name = EnterName();
            int dosage = EnterNumberOfDosage();
            int fullBottle = EnterFullBottleAmount();
            int currentBottle = EnterCurrentBottleAmount();
            double price = EnterPrice();
            successName = data.storeNewMedicine(name, dosage, fullBottle, currentBottle, price);
            if (!successName) {
                System.out.println("core.objects.Medicine already exists!\nTry again!\n");
            }
        } while (!successName);
        System.out.println("Stored a new medicine!");
    }

    private static String EnterName() {
        System.out.println("Enter the name of medicine: ");
        String medicineName;
        do {
            medicineName = scanner.nextLine().trim();
            if (medicineName.isEmpty()) {
                System.out.println("Name cannot be empty!");
            }
        } while (medicineName.isEmpty());
        return medicineName;
    }

    private static int EnterNumberOfDosage() {
        int dosage;
        do {
            System.out.println("Enter number of doses need to be taken: ");
            dosage = Integer.parseInt(scanner.nextLine().trim());
            if (dosage <= 0) {
                System.out.println("Dosage must be greater than 0!");
            }
        } while (dosage <= 0);
        return dosage;
    }

    private static int EnterFullBottleAmount() {
        int fullBottleStat;
        do {
            System.out.println("Enter amount of pills in full bottle: ");
            fullBottleStat = Integer.parseInt(scanner.nextLine().trim());
            if (fullBottleStat <= 0) {
                System.out.println("Full bottle amount must be greater than 0!");
            }
        } while (fullBottleStat <= 0);
        return fullBottleStat;
    }

    private static int EnterCurrentBottleAmount() {
        int currentBottleStat;
        do {
            System.out.println("Enter amount of pills currently in bottle: ");
            currentBottleStat = Integer.parseInt(scanner.nextLine().trim());
            if (currentBottleStat < 0) {
                System.out.println("Current bottle amount cannot be negative!");
            }
        } while (currentBottleStat < 0);
        return currentBottleStat;
    }

    private static double EnterPrice() {
        double medPrice = -1;
        while (medPrice < 0) {
            System.out.println("Enter the exact price of the medication every month: ");
            medPrice = Double.parseDouble(scanner.nextLine().trim());
            if (medPrice < 0) {
                System.out.println("The medication cannot have a negative price. Try again.");
            }
        }
        return medPrice;
    }

    private static void menuEnterSideEffects() {
        String medicine;
        String sideEffect;
        boolean medicineExists;
        boolean success;
        System.out.println("Enter the medicine you wish to add side effects for: ");
        medicine = scanner.nextLine(); //Stores user input on the medicine they entered.
        medicineExists = data.checkExistMedicine(medicine); //Checks to see if the medicine the user entered exists in the program.
        if (!medicineExists) {
            System.out.printf("'%s' does not exists!%n", medicine); //Output if the medicine doesn't exists
        }
        else{
            System.out.println("Enter a side effects, separating each one with a comma. Press enter if none: "); //if the medicine exists, user is asked to list side effects.
            sideEffect = scanner.nextLine(); //store user entered side effects
            success = data.storeMedicineSideEffect(medicine, sideEffect); //checks to see if the side effects are stored
            if (success) {
                System.out.printf("'%s' have been added!%n", sideEffect); //output if the side effects are stored, otherwise, back to menu.
            }

        }
    }

    private static void menuEnterFoodIntake() {
        String medicine;
        String answer;
        boolean medicineExists;
        boolean success;
        System.out.println("Enter medicine you would like to add food intake for: ");
        medicine = scanner.nextLine(); //stores the medicine the user entered
        medicineExists = data.checkExistMedicine(medicine); //checks to see the entered medicine exists on the program
        if (!medicineExists){
            System.out.printf("'%s' does not exists!%n", medicine);//output if the medicine doesn't exist
        }
        else{
            System.out.println("Is medicine taken before meals, after meals, or both? before/after/both: "); //if the medicine exists, user is asked to state the food intake requirement
            answer = scanner.nextLine(); //stores food intake requirements
            success = data.storeMedicineFoodIntake(medicine, answer); //checks to see if the food intake data was stored
            if (success){
                System.out.println("Information has been stored!"); //output if information has been stored
            }
            else{
                System.out.println("Information could not be stored!"); //output of information wasn't able to be stored
            }
        }

    }

    private static void menuEnterViewMedication() {
        String name = EnterName();
        Medicine medicine = data.getMedicationInfo(name);
        if (medicine != null) {
            System.out.println(MEDICINE_HEADER);
            System.out.println(MEDICINE_SEPERATOR);
            System.out.printf(MEDICINE_FORMAT, medicine.getName(), medicine.getDosage(), medicine.getFullBottle(), medicine.getCurrentBottle(), medicine.getPrice());
            System.out.println();
        }
        else{
            System.out.printf("core.objects.Medicine with name '%s' does not exist!%n", name);
        }
    }

    private static void menuEnterViewMedications() {
        System.out.println(MEDICINE_HEADER);
        System.out.println(MEDICINE_SEPERATOR);
        for (Medicine medicine: data.getAllMedicineInfo()){
            System.out.printf(MEDICINE_FORMAT, medicine.getName(), medicine.getDosage(), medicine.getFullBottle(), medicine.getCurrentBottle(), medicine.getPrice());
            System.out.println(); // ADDED NEW LINE FOR BETTER FORMATTING
        }
    }

    private static void menuEnterStatisticsOfRefillingPeriod() {
        int useDays = 0; // Initializing variable to count number of days left to take medicine before refil
        String medName = EnterName(); // Gets name of medicine so that the data already associated with it can be retrieved from core.data.java

        while (useDays <= 0) { // Repeated input sequence that will not end until a specific type of value is entered
            System.out.println("Enter the number of days that occur in between your dosage intake: "); // Needs to know how often dosage is being taken
            useDays = Integer.parseInt(scanner.nextLine().trim()); // Expects an integer that is at least 1
            if (useDays <= 0) {System.out.println("Number of days in between must be at least 1. Try again.");} // Will not allow integers less than 1
        }

        Medicine medicine = data.getMedicationInfo(medName); // Retrieve Object ArrayList from core.Data.java with specific medical info for the name given
        Calculations.getRefillPeriod(medicine);
    }


    private static void menuEnterTotalExpense() { // Output the total amount being spent every month on medication
        double totalExpense = data.getTotalExpense(); // Variable tied to the total medical expense behind paid
        DecimalFormat d = new DecimalFormat("0.00"); // Formatter which will help output values

        if (totalExpense == 0) {
            System.out.println("No expenses at this time!"); // Accounting for no medications existing for which to calculate expenses
        }

        else if (totalExpense > 0) {
            System.out.println("Total expense of all medication: $" + d.format(totalExpense)); // Gives amount being spent on medication if greater than zero
        }
    }

    private static void menuEnterCheapestMedication() {

    }

    private static void menuEnterPriciestMedication() {

    }

    private static void menuDeleteMedication() {
        System.out.println("Enter medicine's name to remove it from the program: ");
        String deleteMedication = EnterName();
        boolean deletionSuccessful = data.deleteMedicine(deleteMedication); // Changed to boolean
        if (deletionSuccessful) { // Check if deletion was successful
            System.out.printf("core.objects.Medicine '%s' successfully deleted!%n", deleteMedication);
        } else {
            System.out.printf("core.objects.Medicine with name '%s' does not exist!%n", deleteMedication);
        }
    }
}


