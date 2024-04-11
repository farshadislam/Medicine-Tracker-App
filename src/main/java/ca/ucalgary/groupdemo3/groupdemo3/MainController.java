/**
 * Written by Hira Asad, Harris Jan, and Farshad Islam
 * Wednesday, April 10th, 2024
 * Tutorial 12 - Adrian Tadic
 */

package ca.ucalgary.groupdemo3.groupdemo3;
import ca.ucalgary.groupdemo3.groupdemo3.objects.FoodIntake;
import ca.ucalgary.groupdemo3.groupdemo3.objects.Medicine;
import ca.ucalgary.groupdemo3.groupdemo3.objects.SideEffects;
import ca.ucalgary.groupdemo3.groupdemo3.util.FileLoader;
import ca.ucalgary.groupdemo3.groupdemo3.util.FileSaver;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;


public class MainController {
    private static final String MEDICINE_FORMAT = "%-20s %-20s %-20s %-20s";
    private static final String INFORMATION_FORMAT = "%-20s %-20s";
    private static final String MEDICINE_HEADER = String.format(MEDICINE_FORMAT, "Name", "Dosage per day", "Full Bottle", "Currently in Bottle", "Price");
    private static final String SIDE_EFFECT_HEADER = String.format(INFORMATION_FORMAT, "Name", "Side Effects");
    private static final String FOOD_INTAKE_HEADER = String.format(INFORMATION_FORMAT, "Name", "Food Intake");

    private static String MEDICINE_SEPERATOR = "";

    static {
        for (int i = 0; i < MEDICINE_HEADER.length(); i++) {
            MEDICINE_SEPERATOR += "-";
        }
    }

    private static Data data = new Data();

    @FXML
    private Button addFoodIntakeButton;

    @FXML
    private TextArea infoTextArea;

    @FXML
    private Label infoLabel;

    @FXML
    private Button addSideEffectsButton;

    @FXML
    private TextArea rightInfoTextArea;

    @FXML
    private Button deleteMedicationButton;

    @FXML
    private Button dosageMGButton;

    @FXML
    private TextField viewMedicineNameTextField, refillMedName;

    @FXML
    private TextField foodIntakeTextField;

    @FXML
    private Button highestExpenseButton;

    @FXML
    private TextField lastPillIntakeTextField;

    @FXML
    private Label leftInfoLabel;

    @FXML
    private Button lowestExpenseButton;

    @FXML
    private TextField madicationNamedelTextField;

    @FXML
    private TextField medicationFINameTextField;

    @FXML
    private TextField medicationMGTextField;

    @FXML
    private TextArea leftInfoTextArea;

    @FXML
    private TextField medicationSENameTextField;

    @FXML
    private Button nextPillTimeButton;

    @FXML
    private TextField pillTimeGapsTextField;

    @FXML
    private Button refillPeriodButton;

    @FXML
    private TextField refillPeriodTextField;

    @FXML
    private Label rightInfoLabel;

    @FXML
    private TextField sideEffectsTextField;

    @FXML
    private Label statusLabel;

    @FXML
    private Button totalExpenseButton;

    @FXML
    private Button viewFoodIntakeButton;

    @FXML
    private Button viewMedicationButton;

    @FXML
    private Button viewSideEffectsButton;

    @FXML
    void onAbout(ActionEvent event) {
        Alert aboutAlert = new Alert(Alert.AlertType.INFORMATION);
        aboutAlert.setTitle("About");
        aboutAlert.setHeaderText("Medicine Tracker for Patient usage");
        aboutAlert.setContentText("Authors: \nHarris Jan\nHira Asad\nFarshad Islam");
        aboutAlert.showAndWait();
    }


    /**
     * Handles the action when the "Add Food Intake" button is clicked.
     * Retrieves the medicine name and food intake answer from text fields,
     * checks if the medicine exists, and then stores the food intake information.
     *
     * @param event The ActionEvent triggered by clicking the "Add Food Intake" button.
     */
    @FXML
    void onAddFoodIntake(ActionEvent event) {
        // Variables to store input and status
        String medicine;
        String answer;
        boolean medicineExists;
        boolean success;

        // Get the medicine name from the medicationFINameTextField
        medicine = medicationFINameTextField.getText();

        // Check if the medicine exists in the program's data
        medicineExists = data.checkExistMedicine(medicine);

        // If medicine doesn't exist, update status label and exit
        if (!medicineExists){
            statusLabel.setText(medicine + " does not exist!");
        }
        else{
            // Get the food intake answer from the foodIntakeTextField
            answer = foodIntakeTextField.getText();

            // Store the food intake information for the medicine
            success = data.storeMedicineFoodIntake(medicine, answer);

            // Update status label based on success of storing information
            if (success){
                statusLabel.setText("Information has been stored!");
            }
            else{
                statusLabel.setText("Information could not be stored!");
            }
        }
    }

    @FXML
    void onAddMedicine(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(MainGUI.class.getResource("add.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 350, 350);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        AddController addM = fxmlLoader.getController();
        addM.establishData(data, this);
        Stage newMed = new Stage();
        newMed.setTitle("Add a new medicine!");
        newMed.setScene(scene);
        newMed.show();
    }

    /**
     * Handles the action when the "Add Side Effects" button is clicked.
     * Retrieves the medicine name and side effect from text fields,
     * checks if the medicine exists, and then stores the side effect information.
     *
     * @param event The ActionEvent triggered by clicking the "Add Side Effects" button.
     */
    @FXML
    void onAddSideEffects(ActionEvent event) {
        // Variables to store input and status
        String medicine;
        String sideEffect;
        boolean medicineExists;
        boolean success;

        // Get the medicine name from the medicationSENameTextField
        medicine = medicationSENameTextField.getText();

        // Check if the medicine exists in the program's data
        medicineExists = data.checkExistMedicine(medicine);

        // If medicine doesn't exist, update status label and exit
        if (!medicineExists) {
            statusLabel.setText(medicine + " does not exist!");
        } else {
            // Get the side effect from the sideEffectsTextField
            sideEffect = sideEffectsTextField.getText();

            // Store the side effect information for the medicine
            success = data.storeMedicineSideEffect(medicine, sideEffect);

            // Update status label based on success of storing side effect
            if (success) {
                statusLabel.setText(sideEffect + " has been added!");
            } else {
                // Output if the side effects are not stored
                statusLabel.setText("Failed to add side effects for " + medicine);
            }
        }
    }

    /**
     * Handles the action when the "Close" button is clicked.
     * Exits the JavaFX application.
     *
     * @param event The ActionEvent triggered by clicking the "Close" button.
     */
    @FXML
    void onClose(ActionEvent event) {
        Platform.exit(); //quits the program
    }

    @FXML
    void onDeleteMedication(ActionEvent event) {
        boolean delSuccess = data.deleteMedicine(madicationNamedelTextField.getText());
        if (delSuccess) {
            viewMedications();
            statusLabel.setText("Medicine has been deleted!");
        } else {
            statusLabel.setText("Medicine could not be deleted!");
        }

    }

    /**
     * Handles the action when the "Load" button is clicked.
     * Opens a file chooser dialog for the user to select a file to load.
     * If a file is selected and successfully loaded, updates the status label.
     * If loading fails or no file is selected, updates the status label accordingly.
     *
     * @param event The ActionEvent triggered by clicking the "Load" button.
     */
    @FXML
    void onLoad(ActionEvent event) {
        // Create a new FileChooser instance
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Data File");

        // Set initial directory to user's home directory
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        // Show open file dialog and wait for user to select a file
        File file = fileChooser.showOpenDialog(new Stage());

        // Setting colours for error and success messages
        statusLabel.setTextFill(Color.RED);
        statusLabel.setTextFill(Color.DARKGREEN);
        statusLabel.setText("");

        // Check if a file was selected
        if (file != null) {
            // Attempt to load the data from the selected file
            Data data = FileLoader.load(file);

            // Check if data loading was successful
            if (data == null) {
                // Update status label if data loading failed
                statusLabel.setTextFill(Color.RED);
                statusLabel.setText("Failed to load data from " + file.getName());
            } else {
                statusLabel.setTextFill(Color.DARKGREEN);
                // Update status label if data was successfully loaded
                statusLabel.setText("Data loaded from " + file.getName());
                MainController.data = data;
            }
        } else {
            // Update status label if no file was selected
            statusLabel.setText("No file selected.");
        }
        viewMedications();
    }

    /**
     * Displays the list of medications in the leftInfoTextArea.
     */
    public void viewMedications() {
        // StringBuilder to construct the output
        StringBuilder sb = new StringBuilder();

        // Append the header for medicine information
        sb.append(MEDICINE_HEADER).append("\n");

        // Iterate through each Medicine object in the data
        for (Medicine medicine : data.getAllMedicineInfo()) {
            // Append formatted medicine information to the StringBuilder
            sb.append(String.format(MEDICINE_FORMAT,
                            medicine.getName(), medicine.getDosage(),
                            medicine.getFullBottle(), medicine.getCurrentBottle(), medicine.getPrice()))
                    .append("\n");
        }

        // Set the text of leftInfoTextArea with the constructed StringBuilder content
        leftInfoTextArea.setText(sb.toString());
    }

    @FXML
    void onLowestExpense(ActionEvent event) {
        ArrayList<Medicine> medicines = data.getAllMedicineInfo();

        if (medicines.isEmpty()) { // No error if medicines is currently empty
            statusLabel.setText("No medicines found! Lowest price is $0.00.");
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

            statusLabel.setText("Lowest priced medicine is " + name + ", at a price of " + min);
        }
    }

    /**
     * Tells users the next time they have to refill their prescriptions based on dosage
     * @param event
     */
    @FXML
    void onRefillPeriod(ActionEvent event) {
        int useDays; // Initializing
        Medicine medicine;
        String endDisplay = "";
        String medName;

        try { // Needs proper integer value
            showAlert("Refill Period", "Enter the number of days between doses which occurs.");
            useDays = Integer.parseInt(showTextInputDialog());
            if (useDays <= 0) { // Will not perform later tasks without proper type
                showAlert("Refill period is too low!", "The refill period must be at least 1 in order for the calculation to occur.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert("Invalid integer for refill period!", "Please enter a proper integer value.");
            return;
        }

        showAlert("Refill Period", "Enter the name of the medicine you want information for.");
        medName = showTextInputDialog();
        if (!data.checkExistMedicine(medName)) { // Must be able to identify a valid medicine by name
            showAlert("Medicine not found!", "Please enter a medicine that already exists!");
            return;
        } else {
            medicine = data.getMedicationInfo(medName); // Retrieves necessary object information
        }

        int currentNum = medicine.getCurrentBottle(); // Grabs current number of pills in bottle
        int dosage = medicine.getDosage(); // Grabs medicine's dosage

        int dosesLeft = (int) (double) (currentNum / dosage); // Gets number of doses left until they can no longer medicate with the current amount
        int daysLeft = useDays * dosesLeft; // Multiplies the number of doses by how often it is taken to find

        int numMonths = (int) Math.floor((double) daysLeft / 30);
        daysLeft %= 30; // Calculates number of months

        int numWeeks = (int) Math.floor((double) daysLeft / 7);
        daysLeft %= 7; // Calculates number of weeks

        int numDays = daysLeft; // Finds number of remaining days past all of that

        if (numMonths == 1) {
            endDisplay = endDisplay + numMonths + " month";
        } else if (numMonths > 1) {
            endDisplay = endDisplay + numMonths + " months";
        } // Outputs number of months remaining

        if (numWeeks > 0 && numMonths > 0) {
            endDisplay = endDisplay + ", ";
        }
        if (numWeeks == 1) {
            endDisplay = endDisplay + numWeeks + " week";
        } else if (numWeeks > 1) {
            endDisplay = endDisplay + numWeeks + " weeks";
        } // Outputs number of weeks remaining

        if (numWeeks > 0 && numDays > 0) {
            endDisplay = endDisplay + ", ";
        }
        if (numDays == 1) {
            endDisplay = endDisplay + numDays + " day";
        } else if (numDays > 1 || (numDays == 0 && numWeeks == 0 && numMonths == 0)) {
            endDisplay = endDisplay + numDays + " days";
        } // Outputs number of days
        endDisplay = endDisplay + ".";

        showAlert("Refill period for " + medName, "Your next refill is in " + endDisplay); // Gives formatted output

    }

    /**
     * Handles the action when the "Save" button is clicked.
     * Opens a file chooser dialog to select a location to save the data.
     *
     * @param event The ActionEvent triggered by clicking the "Save" button.
     */
    @FXML
    void onSave(ActionEvent event) {
        // Create a new FileChooser instance
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Data File");

        // Set initial directory to user's home directory
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        // Show save file dialog and wait for user to select a file
        File file = fileChooser.showSaveDialog(new Stage());
        statusLabel.setTextFill(Color.RED);
        statusLabel.setTextFill(Color.DARKGREEN);
        statusLabel.setText("");

        // Check if a file was selected
        if (file != null) {
            // Attempt to save the data to the selected file
            if (FileSaver.save(file, data)) {
                // Output success message if data is saved
                statusLabel.setTextFill(Color.DARKGREEN);
                statusLabel.setText("Saved to: " + file.getAbsolutePath());
            } else {
                // Output failure message if data failed to save
                statusLabel.setTextFill(Color.RED);
                statusLabel.setText("Failed to save to: " + file.getAbsolutePath());
            }
        } else {
            // Output message if no file was selected
            statusLabel.setText("No file selected.");
        }
    }

    @FXML
    void onTotalExpense(ActionEvent event) {

    }

    /**
     * Handles the action when the "View Food Intake" button is clicked.
     * Displays the list of food intake information in the rightInfoTextArea.
     *
     * @param event The ActionEvent triggered by clicking the "View Food Intake" button.
     */
    @FXML
    void onViewFoodIntake(ActionEvent event) {
        // Clear the rightInfoTextArea before displaying new information
        rightInfoTextArea.clear();

        // StringBuilder to construct the output
        StringBuilder sb = new StringBuilder();

        // Append the header for food intake information
        sb.append(FOOD_INTAKE_HEADER).append("\n");

        // Iterate through each FoodIntake object in the data
        for (FoodIntake foodIntake : data.getAllFoodIntakeInfo()) {
            // Append formatted food intake information to the StringBuilder
            sb.append(String.format(INFORMATION_FORMAT,
                            foodIntake.getName(), foodIntake.getFoodIntake()))
                    .append("\n");
        }

        // Set the text of rightInfoTextArea with the constructed StringBuilder content
        rightInfoTextArea.setText(sb.toString());
    }


    /**
     * Handles the action when the "View Medication" button is clicked.
     * Displays the information of a specific medication in the rightInfoTextArea.
     *
     * @param event The ActionEvent triggered by clicking the "View Medication" button.
     */
    @FXML
    void onViewMedicationButton(ActionEvent event) {
        // Clear the rightInfoTextArea before displaying new information
        rightInfoTextArea.clear();

        // Get the name of the medication from the viewMedicineNameTextField
        String name = viewMedicineNameTextField.getText();

        // Get the Medicine object with the given name from the data
        Medicine medicine = data.getMedicationInfo(name);

        // StringBuilder to construct the output
        StringBuilder sb = new StringBuilder();

        // Check if the Medicine object exists
        if (medicine != null) {
            // Append the header for medication information
            sb.append(MEDICINE_HEADER).append("\n");

            // Append formatted medication information to the StringBuilder
            sb.append(String.format(MEDICINE_FORMAT,
                            medicine.getName(), medicine.getDosage(),
                            medicine.getFullBottle(), medicine.getCurrentBottle(),
                            medicine.getPrice()))
                    .append("\n");
        } else {
            // Output an error message if the Medicine object does not exist
            statusLabel.setText("Medicine with name '" + name + "' does not exist!");
        }

        // Set the text of rightInfoTextArea with the constructed StringBuilder content
        rightInfoTextArea.setText(sb.toString());
    }

    /**
     * Handles the action when the "View Side Effects" button is clicked.
     * Displays the list of side effects information in the rightInfoTextArea.
     *
     * @param event The ActionEvent triggered by clicking the "View Side Effects" button.
     */
    @FXML
    void onViewSideEffectsButton(ActionEvent event) {
        // Clear the rightInfoTextArea before displaying new information
        rightInfoTextArea.clear();

        // StringBuilder to construct the output
        StringBuilder sb = new StringBuilder();

        // Append the header for side effects information
        sb.append(SIDE_EFFECT_HEADER).append("\n");

        // Iterate through each SideEffects object in the data
        for (SideEffects sideEffects : data.getAllSideEffectInfo()) {
            // Append formatted side effects information to the StringBuilder
            sb.append(String.format(INFORMATION_FORMAT,
                            sideEffects.getName(), sideEffects.getSideEffects()))
                    .append("\n");
        }

        // Set the text of rightInfoTextArea with the constructed StringBuilder content
        rightInfoTextArea.setText(sb.toString());
    }

    @FXML
    void onNextPillTime(ActionEvent event) {
        // Get the name of the medicine
        String name = viewMedicineNameTextField.getText();
        // Check if the medicine exists
        if (data.checkExistMedicine(name)) {
            showAlert("Medicine Not Found", name + " does not exist!");
            return;
        }

        // Prompt the user to input the time gaps between each pill intake in hours
        showAlert("Time Gaps", "Enter the time gaps between each pill intake in hours (e.g., 6):");
        int timeGap;
        try {
            timeGap = Integer.parseInt(showTextInputDialog());
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter a valid integer for time gaps.");
            return;
        }

        // Prompt the user to input the time the last pill was taken
        showAlert("Last Pill Time", "Enter the time you took the last pill (e.g., 10:30 AM, 12:00 PM):");
        String lastPillTime = showTextInputDialog();
        if (lastPillTime == null) {
            return; // User canceled the input
        }

        // Calculate the next pill time
        String nextPillTime = Calculations.getNextPillTime(timeGap, lastPillTime);
        // Show the next pill time to the user
        showAlert("Next Pill Time", "You need to take the next pill at " + nextPillTime);
    }
    /**
     * Helper method to display a TextInputDialog and retrieve user input.
     *
     * @return The user input as a string, or null if the dialog was canceled.
     */
    private String showTextInputDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText(null);
        dialog.setContentText("Enter the required information:");
        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }
    /**
     * Display an alert dialog with the specified title and content.
     *
     * @param title   The title of the alert dialog.
     * @param content The content of the alert dialog.
     */
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}