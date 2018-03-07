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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author caseydierking
 */
public class AddPartViewController implements Initializable {

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

    //Instance variables are for new Parts
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
    @FXML private TextField manuLocation;
 
    private boolean isOutsourced;
    
    private String exceptionMessage = new String();

    /**
     *
     */
    /**
     * This code handles the logic for when the "Cancel" button is pushed.
     *
     */
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
        
        
    

    /**
     * This code handles the logic for when the "Save" button is pushed.
     *
     * @param event
     * @throws java.io.IOException
     */
    public void saveButtonPushed(ActionEvent event) throws IOException {

        
        String partName = name.getText();
        String partPrice = price.getText();
        String partInstock = inStock.getText();
        String partMin = min.getText();
        String partMax = max.getText();
        String partLocation = manuLocation.getText();
      //  String partMachineId = machineId.getText();
      //  String partCompanyName = machineId.getText();
        
            //Exception Handling for adding a new part and then if meets criteria, it adds its.
            try {
                exceptionMessage = Part.isPartValid(partName,Double.parseDouble(partPrice),Integer.parseInt(partInstock),Integer.parseInt(partMin), Integer.parseInt(partMax), exceptionMessage);
                if (exceptionMessage.length() > 0) {
                     Alert alert = new Alert(AlertType.INFORMATION);
                     alert.setTitle("Error Adding Part");
                     alert.setHeaderText("Error");
                     alert.setContentText(exceptionMessage);
                     alert.showAndWait();
                     exceptionMessage = "";
            } else {
                if (isOutsourced == false) {
                   

                    Inhouse newPart = new Inhouse(Integer.parseInt(partLocation),partName,Double.parseDouble(partPrice),Integer.parseInt(partInstock),Integer.parseInt(partMin),Integer.parseInt(partMax));
                    Inventory.getAllParts().add(newPart);
                    
                } else {
                    
                    Outsourced newPart = new Outsourced(partLocation,partName,Double.parseDouble(partPrice),Integer.parseInt(partInstock),Integer.parseInt(partMin), Integer.parseInt(partMax));
                    Inventory.getAllParts().add(newPart);
                }

                Parent partsSave = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Scene scene = new Scene(partsSave);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            
        }
            } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("There was an Error adding a Part.");
            alert.setHeaderText("Error");
            alert.setContentText("The form contains blank fields. Please correct and try again.");
            alert.showAndWait();
        }
    }

        
    

    /**
     *
     * This method updates when Inhouse or Outsourced are selected
     */

    public void radioButtonChanged() {

        if (this.partManufactureLocationGroup.getSelectedToggle().equals(this.inHouseButton)) {
            isOutsourced = false;
            toggleLocationLabel.setText("MachineID");
        }

        if (this.partManufactureLocationGroup.getSelectedToggle().equals(this.outsourcedButton)) {
           isOutsourced = true;
            toggleLocationLabel.setText("Company Name");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // These items are for configuring a toggle group for inhouse and outsourced
        partManufactureLocationGroup = new ToggleGroup();
        this.inHouseButton.setToggleGroup(partManufactureLocationGroup);
        this.outsourcedButton.setToggleGroup(partManufactureLocationGroup);

        //This makes sure that both panes aren't showing at run time and autoselects Inhouse to be the default
        inHouseButton.setSelected(true);
        isOutsourced = false;
     
    }
}
