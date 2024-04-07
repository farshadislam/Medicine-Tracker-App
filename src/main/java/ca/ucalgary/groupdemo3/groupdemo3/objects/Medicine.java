package ca.ucalgary.groupdemo3.groupdemo3.objects;

import ca.ucalgary.groupdemo3.groupdemo3.Data;

import java.util.ArrayList;
import java.util.HashSet;
public class Medicine extends Data {
    private static final ArrayList<Object[]> medicines = new ArrayList<>();
    /** Array list for medicinal side effects*/
    private static final ArrayList<Object[]> medicineSideEffects = new ArrayList<>();
    /** Array list for medicinal food intake*/
    private static final ArrayList<Object[]> medicineFoodIntake = new ArrayList<>();
    /** Hashset for medicinal food intake*/
    private static final HashSet<String> names = new HashSet<>();
    /** Array list for medicinal expenses*/
    static final ArrayList<Double> allExpenses = new ArrayList<>();

    String name;
    Integer dosage, fullBottle, currentBottle;
    double price;

    public Medicine(String name, Integer dosage, Integer fullBottle, Integer currentBottle, double price) {
        this.name = name;
        this.dosage = dosage;
        this.fullBottle = fullBottle;
        this.currentBottle = currentBottle;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Integer getDosage() {
        return dosage;
    }

    public Integer getFullBottle() {
        return fullBottle;
    }

    public Integer getCurrentBottle() {
        return currentBottle;
    }

    public double getPrice() {
        return price;
    }

    public boolean checkMedicineAlreadyListed(String name) {
        if (names.contains(name)) {
            return true;//
        }
        return false;
    }

    public String toString() {
        return  "core.objects.Medicine name: " + name + "\n" +
                "core.objects.Medicine dosage: " + dosage + "\n" +
                "Amount in full bottle: " + fullBottle + "\n" +
                "Amount currently in bottle: " + currentBottle + "\n" +
                "core.objects.Medicine price: " + price + "\n";
    }
}
