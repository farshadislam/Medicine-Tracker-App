package ca.ucalgary.groupdemo3.groupdemo3.util;

import ca.ucalgary.groupdemo3.groupdemo3.Data;
import ca.ucalgary.groupdemo3.groupdemo3.objects.FoodIntake;
import ca.ucalgary.groupdemo3.groupdemo3.objects.Medicine;
import ca.ucalgary.groupdemo3.groupdemo3.objects.SideEffects;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaver {
    public static boolean save(File file, Data data) {
        try (FileWriter fw = new FileWriter(file)){
            fw.write("Medicine\n"); // header for medicine info
            for (Medicine medicine: data.getAllMedicineInfo()){ // retrieves all the medicine info to be saved in the file
                // writes the medicine info in file
                fw.write(String.format("%s,%s,%s,%s,%s%n", medicine.getName(), medicine.getDosage(), medicine.getFullBottle(), medicine.getCurrentBottle(), medicine.getPrice()));
            }

            fw.write("Side Effects\n"); // header for side effects info
            for (SideEffects se: data.getAllSideEffectInfo()){ //retrieves all the side effect info
                if (se != null){
                    fw.write(String.format("%s, %s%n", se.getName(), se.getSideEffects())); //write the side effects in the file
                }
            }

            fw.write("Food Intake\n"); // header for food intake
            for (FoodIntake fi: data.getAllFoodIntakeInfo()){ //retrieves all food intake info
                if (fi != null) {
                    String inputInfo = "";
                    String [] info = fi.getFoodIntake().split("=");
                    // separates each value with a comma
                    for (String element: info){
                        if (inputInfo == ""){
                            inputInfo = element;
                        } else {
                            inputInfo = inputInfo + "," + element; //separates elements by a comma
                        }
                    }

                    fw.write(String.format("%s%n", inputInfo)); //writes all food intake in file
                }
            }

            return true; // if info has been successfully saved
        } catch (IOException e){
            return false; //if info has not been saved
        }
    }
}

