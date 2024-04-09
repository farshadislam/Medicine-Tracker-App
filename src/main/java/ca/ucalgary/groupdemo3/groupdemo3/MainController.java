package ca.ucalgary.groupdemo3.groupdemo3;

import ca.ucalgary.groupdemo3.groupdemo3.objects.Medicine;
import ca.ucalgary.groupdemo3.groupdemo3.objects.SideEffects;
import ca.ucalgary.groupdemo3.groupdemo3.util.FileLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class MainController {
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

    }

    @FXML
    void onAddFoodIntake(ActionEvent event) {

    }

    @FXML
    void onAddMedicine(ActionEvent event) {

    }

    @FXML
    void onAddSideEffects(ActionEvent event) {

    }

    @FXML
    void onClose(ActionEvent event) {

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
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        // Check if a file was selected
        if (selectedFile != null) {
            // Attempt to load the data from the selected file
            Data data = FileLoader.load(selectedFile);

            // Check if data loading was successful
            if (data == null) {
                // Update status label if data loading failed
                statusLabel.setText("Failed to load data from " + selectedFile.getName());
            } else {
                // Update status label if data was successfully loaded
                statusLabel.setText("Data loaded from " + selectedFile.getName());
                MainController.data = data;
            }
        } else {
            // Update status label if no file was selected
            statusLabel.setText("No file selected.");
        }
        viewMedications();
    }

    private void viewMedications() {
        StringBuilder sb = new StringBuilder();
        sb.append(MEDICINE_HEADER);
        //sb.append(MEDICINE_SEPERATOR);
        sb.append("\n");
        for (Medicine medicine: data.getAllMedicineInfo()){
            sb.append(String.format(MEDICINE_FORMAT, medicine.getName(), medicine.getDosage(), medicine.getFullBottle(), medicine.getCurrentBottle(), medicine.getPrice()));
        }
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

    @FXML
    void onSave(ActionEvent event) {

    }

    @FXML
    void onTotalExpense(ActionEvent event) {

    }

    @FXML
    void onViewFoodIntake(ActionEvent event) {

    }

    @FXML
    void onViewMedicationButton(ActionEvent event) {


    }

    private void viewMedicationsSideEffects() {
        StringBuilder sb = new StringBuilder();
        sb.append(SIDE_EFFECT_HEADER);
        sb.append("\n");
        for (SideEffects side_effects: data.getAllSideEffectInfo()){
            sb.append(String.format(INFORMATION_FORMAT, side_effects.getName(), side_effects.getSideEffects()));
        }
        rightInfoTextArea.setText(sb.toString());
    }

    @FXML
    void onViewSideEffectsButton(ActionEvent event) {
        rightInfoTextArea.clear();
        viewMedicationsSideEffects();
    }

}