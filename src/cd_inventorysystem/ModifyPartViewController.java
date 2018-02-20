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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private Label machineIdLabel;
    @FXML
    private Label companyNameLabel;
    @FXML
    private TextField companyName;
   
    private boolean isInhouse;

    private boolean outsourced;
    
 
    @FXML
    private Part selectedPart;
    

    //This method initializes the part for editing
    public void initData(Part p) {

        //Set part that was passed in to selectedPart
        selectedPart = p;

        name.setText(selectedPart.getName().getValue());
        price.setText(selectedPart.getPrice().getValue().toString());
        inStock.setText(selectedPart.getInStock().getValue().toString());
        min.setText(selectedPart.getMin().getValue().toString());
        max.setText(selectedPart.getMax().getValue().toString());

        if (selectedPart instanceof Inhouse) {
            
            //Cast Inhouse and Set correct labels for Inhouse Part
            Inhouse TempIn = (Inhouse) selectedPart;
            machineIdLabel.setVisible(true);
            companyNameLabel.setVisible(false);
            companyName.setVisible(false);
            machineId.setText(TempIn.getMachineId().getValue().toString());
            machineId.setEditable(true);
            machineIdLabel.setText("Machine Id");
            isInhouse = true;
            outsourced = false;
            inHouseButton.setSelected(true);
            
        } else {
           
            //Set correct labels for Outsourced Part
            Outsourced TempOut = (Outsourced) selectedPart;
            machineIdLabel.setVisible(false);
            machineId.setVisible(false);
            companyNameLabel.setVisible(true);
            companyName.setText(TempOut.getCompanyName().getValue());
            companyNameLabel.setText("Company Name");
            isInhouse = false;
            outsourced = true;
            outsourcedButton.setSelected(true);
            
            //Populate TextFields for editing with current data
        }

    }

    public void saveModifiedPart(ActionEvent event) throws IOException {

        //Logic to see if part is part of Inhouse Group
        if (isInhouse && !outsourced) {
            //Cast selectedPart to Inhouse
            Inhouse TempIn = (Inhouse) selectedPart;

            //Setup Variables for saving
            String tempPartName = name.getText();
            double tempPrice = Double.parseDouble(price.getText());
            int tempInstock = Integer.parseInt(inStock.getText());
            int tempMin = Integer.parseInt(min.getText());
            int tempMax = Integer.parseInt(max.getText());
            int tempMachineId = Integer.parseInt(machineId.getText().trim());

            
           //Part modifiedPart = new Inhouse(tempMachineId,tempPartName,tempPrice,tempInstock,tempMin,tempMax);
           //Inventory.getAllParts().add(modifiedPart);
          // Inventory.removePart(selectedPart);
            //Update the part
            TempIn.setName(tempPartName);
            TempIn.setPrice(tempPrice);
            TempIn.setInStock(tempInstock);
            TempIn.setMin(tempMin);
            TempIn.setMax(tempMax);
            TempIn.setMachineId(tempMachineId);

            
        } 
        
        
        if(!isInhouse && outsourced){

            //Cast selectedPart to Outsourced
            
            //Setup Variables for saving
            String tempPartName = name.getText();
            double tempPrice = Double.parseDouble(price.getText());
            int tempInstock = Integer.parseInt(inStock.getText());
            int tempMin = Integer.parseInt(min.getText());
            int tempMax = Integer.parseInt(max.getText());
            String tempCompanyName = companyName.getText();

            //Update the part
            
           Part modifiedPart = new Outsourced(tempCompanyName,tempPartName,tempPrice,tempInstock,tempMin,tempMax);
           Inventory.getAllParts().add(modifiedPart);
           Inventory.removePart(selectedPart);

            
            
        }
        
        //Send the user back to the main page
        Parent savedParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene addPartScene = new Scene(savedParent);

        //Get Stage Information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(addPartScene);
        window.show();
    }

    /**
     * This code handles the logic for when the "add Part" button is pushed.
     *
     */
    public void radioButtonChanged() {
        

        if(this.partManufactureLocationGroup.getSelectedToggle().equals(this.inHouseButton)) {
              //Cast Inhouse and Set correct labels for Inhouse Part

            machineIdLabel.setVisible(true);
            machineId.setVisible(true);
            machineId.setEditable(true);
            companyNameLabel.setVisible(false);
            companyName.setVisible(false);
;            //machineId.setText(TempIn.getMachineId().getValue().toString());
            
            isInhouse = true;
            outsourced = false;

        } else {
            machineIdLabel.setVisible(false);
            machineId.setVisible(false);
            companyNameLabel.setVisible(true);
            companyName.setVisible(true);
            
          isInhouse = false;
          outsourced = true;

        }
    }

    public void cancelButtonPushed(ActionEvent event) throws IOException {
        Parent cancelParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene addPartScene = new Scene(cancelParent);

        //Get Stage Information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(addPartScene);
        window.show();
    }
    
    
     
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // These items are for configuring a toggle group for inhouse and outsourced
        partManufactureLocationGroup = new ToggleGroup();
        this.inHouseButton.setToggleGroup(partManufactureLocationGroup);
        this.outsourcedButton.setToggleGroup(partManufactureLocationGroup);
        

        //This makes sure that both panes aren't showing at run time and autoselects Inhouse to be the default
      
        
       

    }

}
