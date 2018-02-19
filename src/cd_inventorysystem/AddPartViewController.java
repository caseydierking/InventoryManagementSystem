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
    @FXML private TextField nameOutsourced;
    @FXML private TextField priceOutsourced;
    @FXML private TextField inStockOutsourced;
    @FXML private TextField minOutsourced;
    @FXML private TextField maxOutsourced;
    @FXML private TextField machineId;
    @FXML private TextField companyName;
    @FXML private Pane  inhousePane;
    @FXML private Pane  outsourcedPane;

    
    
            
          
    
    
    
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
            //Get text field's info and save it to a new Inhouse Object. Then add to the array.
            String tempPartName = name.getText();
            int tempMachineId = Integer.parseInt(machineId.getText().trim());
            double tempPrice = Double.parseDouble(price.getText());
            int tempInstock = Integer.parseInt(inStock.getText());
            int tempMin = Integer.parseInt(min.getText());
            int tempMax = Integer.parseInt(max.getText());
            //int tempPrice = (price.getText());
           Part newPart = new Inhouse(tempMachineId,tempPartName,tempPrice,tempInstock,tempMin,tempMax);
           Inventory.getAllParts().add(newPart);
           } else {
               
            String tempPartName = nameOutsourced.getText();
            double tempPrice = Double.parseDouble(priceOutsourced.getText());
            int tempInstock = Integer.parseInt(inStockOutsourced.getText());
            int tempMin = Integer.parseInt(minOutsourced.getText());
            int tempMax = Integer.parseInt(maxOutsourced.getText());
            String tempCompanyName = companyName.getText();

            //int tempPrice = (price.getText());
           Part newPart = new Outsourced(tempCompanyName,tempPartName,tempPrice,tempInstock,tempMin,tempMax);
           Inventory.getAllParts().add(newPart);
           
           }
          
             // public Inhouse(int machineId, String name, double price, int inStock, int min, int max) {

     //   }
            
        
       
    
    
    }
    /**
     * 
     * This method updates when Inhouse or Outsourced are selected
     */
    
    public void radioButtonChanged(){
        
        if(this.partManufactureLocationGroup.getSelectedToggle().equals(this.inHouseButton)){
            inhousePane.setVisible(true);
            outsourcedPane.setVisible(false);
        } 
        
        if(this.partManufactureLocationGroup.getSelectedToggle().equals(this.outsourcedButton)){
            inhousePane.setVisible(false);
            outsourcedPane.setVisible(true);
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
        inhousePane.setVisible(true);
        outsourcedPane.setVisible(false);

        
    }    
    
}
