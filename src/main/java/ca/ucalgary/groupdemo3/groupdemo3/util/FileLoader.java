package ca.ucalgary.groupdemo3.groupdemo3.util;

import ca.ucalgary.groupdemo3.groupdemo3.Data;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileLoader {
    public static Data load(File file) {
        Data data = new Data();
        try (Scanner scanner = new Scanner(file)) {
            String line = scanner.nextLine().trim(); // Read the first line
            if (!line.equals("Medicine")) { // Check if the header is "Medicine"
                System.err.println("Medicine header was not found");
                return null;
            }

            while (scanner.hasNextLine()) {
                if (line.equals("Medicine")) { // Check if the next section is "Medicine"
                    while (scanner.hasNextLine()) {
                        line = scanner.nextLine().trim();
                        if (line.equals("Side Effects")) {// Check if the next section is "Side Effects"
                            break; // Exit the current loop to move to "Side Effects" section
                        }
                        String[] info = line.split(",");
                        if (info.length != 5) {
                            return null; // Invalid data format
                        }
                        String name = info[0];
                        int dosage = Integer.parseInt(info[1]);
                        int fullBottle = Integer.parseInt(info[2]);
                        int currentBottle = Integer.parseInt(info[3]);
                        double price = Double.parseDouble(info[4]);
                        data.storeNewMedicine(name, dosage, fullBottle, currentBottle, price);
                    }
                }

                if (line.equals("Side Effects")) { //checks to see if the header os "side effects"
                    while (scanner.hasNextLine()) {
                        line = scanner.nextLine().trim();
                        if (line.equals("Food Intake")) {
                            break; // Exit the current loop to move to "Food Intake" section
                        }
                        String[] info = line.split(", ");
                        if (info.length < 2){
                            break;
                        }
                        String name = info[0];
                        String sideEffects = info[1];
                        for (int i = 2; i < info.length; i++) {
                            sideEffects += ", " + info[i];
                        }
                        data.storeMedicineSideEffect(name, sideEffects);
                    }
                }


                if (line.equals("Food Intake")) { //checks to see if the header is "food intake"
                    while (scanner.hasNextLine()) {
                        line = scanner.nextLine().trim();
                        String[] info = line.split(",");
                        if (info.length != 2) {
                            break; // Invalid data format
                        }
                        String name = info[0];
                        String foodIntake = info[1].trim();
                        String foodIntakeAnswer = null;
                        //checks the input for storing food intake
                        if (foodIntake.equals("Take medicine before meal.")) {
                            foodIntakeAnswer = "before";
                        } else if (foodIntake.equals("Take medicine after meal.")) {
                            foodIntakeAnswer = "after";
                        } else if (foodIntake.equals("Take medicine before and after meal.")) {
                            foodIntakeAnswer = "both";
                        }
                        data.storeMedicineFoodIntake(name, foodIntakeAnswer);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error occurred while reading the file.");
            //return null; // IO exception occurred
        } catch (NumberFormatException e) {
            System.err.println("Error occurred while parsing values.");
            //return null; // Number format exception occurred
        }
        return data; // File was successfully loaded
    }
}