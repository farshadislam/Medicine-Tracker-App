package ca.ucalgary.groupdemo3.groupdemo3;

import ca.ucalgary.groupdemo3.groupdemo3.objects.FoodIntake;
import ca.ucalgary.groupdemo3.groupdemo3.objects.Medicine;
import ca.ucalgary.groupdemo3.groupdemo3.objects.SideEffects;

import java.util.*;

public class Data {
    /** Array list for this.medicines*/
    private final ArrayList<Medicine> medicines;
    /** Array list for medicinal side effects*/
    private final ArrayList<SideEffects> medicinesideEffects;
    /** Array list for medicinal food intake*/
    private final ArrayList<FoodIntake> medicineFoodIntake;
    /** Hashset for medicinal food intake*/

    public Data() {
        medicines = new ArrayList<>();
        medicinesideEffects = new ArrayList<>();
        /** Array list for medicinal food intake*/
        medicineFoodIntake = new ArrayList<>();
        /** Hashset for medicinal food intake*/
    }
    /*
    Function to store this.medicines
     */
    public boolean storeNewMedicine(String name, Integer dosage, Integer fullBottle, Integer currentBottle, double price) {
        if (!checkExistMedicine(name)) {
            Medicine medicine = new Medicine(name, dosage, fullBottle, currentBottle, price);
            this.medicines.add(medicine);
            return true;
        } else {
            return false;
        }
    }

    /*
  Function to check existing medicine
   */
    public boolean checkExistMedicine(String name) {
        for (Medicine medicine : this.medicines) {
            if (medicine.getName().equals(name)) { // Checking hash set
                return true;
            }
        }
        return false;
    }

    /*
  Function to view all medicinal info
   */
    public ArrayList<Medicine> getAllMedicineInfo() {
        return this.medicines;
    }
    /** Function to view all medicinal side effects info */
    public ArrayList<SideEffects> getAllSideEffectInfo() { return this.medicinesideEffects; } //returns all side effect information
    /** Function to view all medicinal food intake info */
    public ArrayList<FoodIntake> getAllFoodIntakeInfo() {return medicineFoodIntake;} //returns all food intake information

    /*
Function to view specific medicinal info
*/
    public Medicine getMedicationInfo(String name) {
        for (Medicine medicine : this.medicines) {
            if (medicine.getName().equals(name)) { // Checking hash set
                return medicine;
            }
        }
        return null;
    }

    /** Deleting medication function*/
    public boolean deleteMedicine(String name) {
        for (Medicine medicine : this.medicines) {
            if (medicine.getName().equals(name)) {
                this.medicines.remove(medicine); // Remove the entire medicine array
                return true; // Return true to indicate successful deletion
            }
        }
        return false; // Return false if medicine not found
    }

    /** Function to storing medicine side effects */
    public boolean storeMedicineSideEffect(String med, String sideEffects) {
        // Check if sideEffects is not empty
        if (!sideEffects.isEmpty()) {
            // Split the sideEffects string by comma into an array
            String[] sideEffectsSplit = sideEffects.split(",");
            // Create an ArrayList to store the split side effects
            ArrayList<String> storageSE = new ArrayList<>();
            // Add all split side effects to the ArrayList
            Collections.addAll(storageSE, sideEffectsSplit);

            // Create a HashMap to store the medicine name as key and side effects ArrayList as value
            HashMap<String, ArrayList> paramSE = new HashMap<>();
            paramSE.put(med, storageSE);

            // Loop through the list of medicines
            for (Medicine medicine : this.medicines) {
                // Check if the medicine name matches the provided 'med'
                if (med.equals(medicine.getName())) {
                    // Get medicine details: dosage, full bottle, current bottle, price
                    Integer d = medicine.getDosage();
                    Integer f = medicine.getFullBottle();
                    Integer c = medicine.getCurrentBottle();
                    double p = medicine.getPrice();

                    // Create a new SideEffects object with medicine details and side effects
                    SideEffects s = new SideEffects(med, d, f, c, p, paramSE);

                    // Add the SideEffects object to the list of medicine side effects
                    this.medicinesideEffects.add(s);

                    // Return true to indicate successful storage
                    return true;
                }
            }
        }
        // Return false if side effects were empty or medicine name was not found
        return false;
    }

    /** Function to storing medicine food intake */
    public boolean storeMedicineFoodIntake(String med, String answer) {
        // Create a HashMap to store the medicine name as key and food intake answer as value
        HashMap<String, String> paramFI = new HashMap<>();
        paramFI.put(med, answer);

        // Switch statement based on the food intake answer
        switch (answer) {
            case "before":
                // Update the food intake answer for "before"
                paramFI.put(med, "Take medicine before meal.");
                // Loop through the list of medicines
                for (Medicine medicine : this.medicines) {
                    // Check if the medicine name matches the provided 'med'
                    if (med.equals(medicine.getName())) {
                        // Get medicine details: dosage, full bottle, current bottle, price
                        Integer d = medicine.getDosage();
                        Integer f = medicine.getFullBottle();
                        Integer c = medicine.getCurrentBottle();
                        double p = medicine.getPrice();

                        // Create a new FoodIntake object with medicine details and food intake
                        FoodIntake fi = new FoodIntake(med, d, f, c, p, paramFI);

                        // Add the FoodIntake object to the list of medicine food intake
                        medicineFoodIntake.add(fi);
                    }
                }
                // Return true to indicate successful storage
                return true;
            case "after":
                // Update the food intake answer for "after"
                paramFI.put(med, "Take medicine after meal.");
                // Loop through the list of medicines
                for (Medicine medicine : this.medicines) {
                    // Check if the medicine name matches the provided 'med'
                    if (med.equals(medicine.getName())) {
                        // Get medicine details: dosage, full bottle, current bottle, price
                        Integer d = medicine.getDosage();
                        Integer f = medicine.getFullBottle();
                        Integer c = medicine.getCurrentBottle();
                        double p = medicine.getPrice();

                        // Create a new FoodIntake object with medicine details and food intake
                        FoodIntake fi = new FoodIntake(med, d, f, c, p, paramFI);

                        // Add the FoodIntake object to the list of medicine food intake
                        medicineFoodIntake.add(fi);
                    }
                }
                // Return true to indicate successful storage
                return true;
            case "both":
                // Update the food intake answer for "both"
                paramFI.put(med, "Take medicine before and after meal.");
                // Loop through the list of medicines
                for (Medicine medicine : this.medicines) {
                    // Check if the medicine name matches the provided 'med'
                    if (med.equals(medicine.getName())) {
                        // Get medicine details: dosage, full bottle, current bottle, price
                        Integer d = medicine.getDosage();
                        Integer f = medicine.getFullBottle();
                        Integer c = medicine.getCurrentBottle();
                        double p = medicine.getPrice();

                        // Create a new FoodIntake object with medicine details and food intake
                        FoodIntake fi = new FoodIntake(med, d, f, c, p, paramFI);

                        // Add the FoodIntake object to the list of medicine food intake
                        medicineFoodIntake.add(fi);
                    }
                }
                // Return true to indicate successful storage
                return true;
            default:
                // Return false for invalid food intake answers
                return false;
        }
    }

    public double getTotalExpense() { // Calculate total price of all monthly medication
        /** Function to retrieve total expenses
         * @returns double totalPay, containing the sum of all medical expenses combined
         */
        double totalPay = 0; // Initialize return variable
        for (Medicine medicine : medicines) { // Iterate through allExpenses Double ArrayList
            totalPay = totalPay + medicine.getPrice(); // Add every single entry together
        }

        return totalPay; // Return the sum of every medications' monthly price
    }

    public Object[] getLowestExpense() {
        /**
         * Gets lowest expensed medicine
         * @returns Object[] array with the name of the lowest priced medication and its respective price
         */
        if (medicines.isEmpty()) { // No error if medicines is currently empty
            return new Object[]{"heehee", 0.00}; // Returns double which is recognized in Menu.java for signalling not to do further processing
        }

        else {
            String name = medicines.get(0).getName(); // Initialization
            double min = medicines.get(0).getPrice();

            for (Medicine medicine : medicines) { // Finding lowest priced medicine and obtaining attributes
                if (medicine.getPrice() < min) {
                    min = medicine.getPrice(); // Sets price and name of cheapest item in medicines
                    name = medicine.getName();
                }
            }

            Object[] attributes = new Object[2]; // Object[] array initialization
            attributes[0] = name; // Set name
            attributes[1] = min; // Set price

            return attributes; // Return array with two indices
        }
    }

    public Object[] getHighestExpense() {
        /**
         * Gets highest expensed medicine
         * @returns Object[] array with the name of the highest priced medication and its respective price
         */
        if (medicines.isEmpty()) {
            return new Object[]{"heehee", 0.00};
        }

        else {
            String name = medicines.get(0).getName();
            double max = medicines.get(0).getPrice();
            for (Medicine medicine : medicines) { // Structure is identical to getLowestExpense() but this time it scans for the highest value in the for-loop
                if (medicine.getPrice() > max) {
                    max = medicine.getPrice();
                    name = medicine.getName();
                }
            }

            Object[] attributes = new Object[2];
            attributes[0] = name;
            attributes[1] = max;

            return attributes;
        }
    }
}
