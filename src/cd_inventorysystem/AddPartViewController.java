/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cd_inventorysystem;

import cd_inventorysystem.Models.Inhouse;
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
public class AddPartViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private RadioButton inHouseButton;
    @FXML private RadioButton outsourcedButton;
    @FXML private Label toggleLocationLabel;
    private ToggleGroup partManufactureLocationGroup;

    //Instance variables are for new Parts
    @FXML private TextField name;
    @FXML private TextField price;
    @FXML private TextField inStock;
    @FXML private TextField min;
    @FXML private TextField max;
    @FXML private TextField machineId;
    
            
          
    
    
    
    /**
     * 
     */
    
    
    
    
      /** 
     * This code handles the logic for when the "Cancel" button is pushed.
     *
     */
    
    public void  cancelButtonPushed(ActionEvent event) throws IOException{
        Parent cancelParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene addPartScene = new Scene(cancelParent);
        
        //Get Stage Information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addPartScene);
        window.show();
    }
    
     /** 
     * This code handles the logic for when the "Save" button is pushed.
     *
     */
    
    public void  saveButtonPushed(ActionEvent event) throws IOException{
        if(this.partManufactureLocationGroup.getSelectedToggle().equals(this.inHouseButton)){
            //Get the string entered into machineID and conver to Int for new object creation
            int tempMachineId = Integer.parseInt(machineId.getText());
            int tempPrice = (price.getText());
            
            
            
            
          Part newPart = new Inhouse(tempMachineId,"testpart",1,50,1,1);

          
          
              public Inhouse(int machineId, String name, double price, int inStock, int min, int max) {

        }
            
        
       
    }
    
    
    /**
     * 
     * This method updates when Inhouse or Outsourced are selected
     */
    
    public void radioButtonChanged(){
        if(this.partManufactureLocationGroup.getSelectedToggle().equals(this.inHouseButton)){
            toggleLocationLabel.setText("Machine ID");
        } else {
            toggleLocationLabel.setText("Company name");
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
