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
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.layout.Pane;
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
    private TextField nameOutsourced;
    @FXML
    private TextField priceOutsourced;
    @FXML
    private TextField inStockOutsourced;
    @FXML
    private TextField minOutsourced;
    @FXML
    private TextField maxOutsourced;
    @FXML
    private TextField machineId;
    @FXML
    private TextField location;
    @FXML
    private TextField companyName;
    @FXML
    private Pane inhousePane;
    @FXML
    private Pane outsourcedPane;
    private boolean isInhouse;
    private boolean isSaved;
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
            isInhouse = true;
            outsourced= false;
            //Cast selectedPart to Inhouse
            Inhouse TempIn = (Inhouse) selectedPart;
            //Populate TextFields for editing with current data
            machineId.setText(TempIn.getMachineId().getValue().toString());
            //Set Machine Id
            //  TempIn.setMachineId(Integer.parseInt(machineId.getText().trim()));
        } else {
            isInhouse = false;
            outsourced = true;

            //Cast selectedPart to Inhouse
            Outsourced TempOut = (Outsourced) selectedPart;
            machineId.setText(TempOut.getCompanyName().getValue());

            //Populate TextFields for editing with current data
        }

    }

    public void saveModifiedPart() {

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
            Outsourced TempOut = (Outsourced) selectedPart;
            
            //Setup Variables for saving
            String tempPartName = name.getText();
            double tempPrice = Double.parseDouble(price.getText());
            int tempInstock = Integer.parseInt(inStock.getText());
            int tempMin = Integer.parseInt(min.getText());
            int tempMax = Integer.parseInt(max.getText());
            String tempCompanyName = companyName.getText();

            //Update the part
            TempOut.setName(tempPartName);
            TempOut.setPrice(tempPrice);
            TempOut.setInStock(tempInstock);
            TempOut.setMin(tempMin);
            TempOut.setMax(tempMax);
            TempOut.setCompanyName(tempCompanyName);
            
        }

    }

    /**
     * This code handles the logic for when the "add Part" button is pushed.
     *
     */
    public void radioButtonChanged() {

        if(this.partManufactureLocationGroup.getSelectedToggle().equals(this.inHouseButton)) {
            toggleLocationLabel.setText("MachineId");
            isInhouse = true;
            outsourced = false;

        }

        if(this.partManufactureLocationGroup.getSelectedToggle().equals(this.outsourcedButton)) {
            toggleLocationLabel.setText("Company Name");
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
        inHouseButton.setSelected(true);

    }

}
