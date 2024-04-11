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
    private TextField viewMedicineNameTextField;

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

    }

    @FXML
    void onDosageMG(ActionEvent event) {

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

    }

    @FXML
    void onNextPillTime(ActionEvent event) {

    }

    @FXML
    void onRefillPeriod(ActionEvent event) {

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

}