/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cd_inventorysystem;

import cd_inventorysystem.Models.Inhouse;
import cd_inventorysystem.Models.Inventory;
import cd_inventorysystem.Models.Outsourced;
import cd_inventorysystem.Models.Part;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author caseydierking
 */
public class ModifyPartViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private RadioButton inHouseButton;
    @FXML
    private RadioButton outsourcedButton;
    @FXML
    private Label toggleLocationLabel;
    private ToggleGroup partManufactureLocationGroup;
    @FXML
    private TextField name;
    @FXML
    private TextField price;
    @FXML
    private TextField inStock;
    @FXML
    private TextField min;
    @FXML
    private TextField max;
    @FXML
    private TextField machineId;
    @FXML
    private Label partIDLabel;
    private boolean isOutsourced;
    private String exceptionMessage = new String();

    @FXML
    private Part selectedPart;

    //This method initializes the part for editing
    public void initData(Part p) {

        //Set part that was passed in to selectedPart
        selectedPart = p;

        name.setText(selectedPart.getName().getValue());
        partIDLabel.setText(selectedPart.getPartID().getValue().toString());
        price.setText(selectedPart.getPrice().getValue().toString());
        inStock.setText(selectedPart.getInStock().getValue().toString());
        min.setText(selectedPart.getMin().getValue().toString());
        max.setText(selectedPart.getMax().getValue().toString());

        if (selectedPart instanceof Inhouse) {

            //Cast Inhouse and Set correct labels for Inhouse Part
            Inhouse TempIn = (Inhouse) selectedPart;

            machineId.setText(TempIn.getMachineId().getValue().toString());
            toggleLocationLabel.setText("MachineID");
            inHouseButton.setSelected(true);

        } else {

            //Set correct labels for Outsourced Part
            Outsourced TempOut = (Outsourced) selectedPart;

            machineId.setText(TempOut.getCompanyName().getValue());
            toggleLocationLabel.setText("Company Name");
            outsourcedButton.setSelected(true);

        }

    }

    public void saveModifiedPart(ActionEvent event) throws IOException {

        String partName = name.getText();
        String partPrice = price.getText();
        String partInstock = inStock.getText();
        String partMin = min.getText();
        String partMax = max.getText();
        String partMachineId = machineId.getText();
        String partCompanyName = machineId.getText();

        try {
            exceptionMessage = Part.isPartValid(partName, Double.parseDouble(partPrice), Integer.parseInt(partInstock), Integer.parseInt(partMin), Integer.parseInt(partMax), exceptionMessage);
            if (exceptionMessage.length() > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error Adding Part");
                alert.setHeaderText("Error");
                alert.setContentText(exceptionMessage);
                alert.showAndWait();
                exceptionMessage = "";
            } else {
                if (isOutsourced == false) {

                    Inhouse newPart = new Inhouse(Integer.parseInt(partMachineId), partName, Double.parseDouble(partPrice), Integer.parseInt(partInstock), Integer.parseInt(partMin), Integer.parseInt(partMax));
                    Inventory.getAllParts().add(newPart);
                    Inventory.removePart(selectedPart);

                } else {
                    Outsourced newPart = new Outsourced(partCompanyName, partName, Double.parseDouble(partPrice), Integer.parseInt(partInstock), Integer.parseInt(partMin), Integer.parseInt(partMax));
                    Inventory.getAllParts().add(newPart);
                    Inventory.removePart(selectedPart);

                }

                Parent partsSave = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Scene scene = new Scene(partsSave);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();

            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("There was an Error adding a Part.");
            alert.setHeaderText("Error");
            alert.setContentText("The form contains blank fields. Please correct and try again.");
            alert.showAndWait();
        }
    }

    /**
     * This code handles the logic for when the "add Part" button is pushed.
     *
     */
    public void radioButtonChanged() {

        if (this.partManufactureLocationGroup.getSelectedToggle().equals(this.inHouseButton)) {

            isOutsourced = false;
            toggleLocationLabel.setText("MachineID");

        } else {

            isOutsourced = true;
            toggleLocationLabel.setText("Company Name");

        }
    }

    public void cancelButtonPushed(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("Are you sure you want to cancel?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            Parent cancelParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene addPartScene = new Scene(cancelParent);

            //Get Stage Information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(addPartScene);
            window.show();
        } else {
            alert.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // These items are for configuring a toggle group for inhouse and outsourced
        partManufactureLocationGroup = new ToggleGroup();
        this.inHouseButton.setToggleGroup(partManufactureLocationGroup);
        this.outsourcedButton.setToggleGroup(partManufactureLocationGroup);

    }

}
