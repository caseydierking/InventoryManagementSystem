/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cd_inventorysystem;

import cd_inventorysystem.Models.Inhouse;
import cd_inventorysystem.Models.Inventory;
import static cd_inventorysystem.Models.Inventory.removePart;
import cd_inventorysystem.Models.Outsourced;
import cd_inventorysystem.Models.Part;
import cd_inventorysystem.Models.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author caseydierking
 */



public class AddProductViewController implements Initializable {
    
    @FXML private TableView<Part> partTable;
    @FXML private TableColumn<Part, Integer> partIdColumn;
    @FXML private TableColumn<Part, String> partNameColumn;
    @FXML private TableColumn<Part, Integer> partInStockColumn;
    @FXML private TableColumn<Part, Double> partPriceColumn;
    
    
    @FXML private TableView<Part> associatedPartsTable;
    @FXML private TableColumn<Part, Integer> associatedPartsIDColumn;
    @FXML private TableColumn<Part, String> associatedPartsNameColumn;
    @FXML private TableColumn<Part, Integer> associatedPartsInStockColumn;
    @FXML private TableColumn<Part, Double> associatedPartsPriceColumn;
    
    
    @FXML private TextField productName;
    @FXML private TextField productPrice;
    @FXML private TextField productInStock;
    @FXML private TextField productMin;
    @FXML private TextField productMax;
    
    private ObservableList<Part> currentParts = FXCollections.observableArrayList();


    public void  saveProductButtonPushed(ActionEvent event) throws IOException{
            //Create a new produc tobject and set variables to the text the user inputs
            Product newProduct = new Product();
            String tempProductName = productName.getText();
            double tempPrice = Double.parseDouble(productPrice.getText());
            int tempInstock = Integer.parseInt(productInStock.getText());
            int tempMin = Integer.parseInt(productMin.getText());
            int tempMax = Integer.parseInt(productMax.getText());
         
            //Set values for the product
            newProduct.setName(tempProductName);
            newProduct.setPrice(tempPrice);
            newProduct.setInStock(tempInstock);
            newProduct.setMin(tempMin);
            newProduct.setMax(tempMax);
            
            //Create a new Array list and get parts from the associatedParts table
            ArrayList<Part> parts = new ArrayList<>();
            parts.addAll(associatedPartsTable.getItems());
            newProduct.addAssociatedParts(parts);

            //save Product to Memory
            Inventory.addProduct(newProduct);
            
            
             //Send the user back to the main page
        Parent savedParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene addPartScene = new Scene(savedParent);

        //Get Stage Information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(addPartScene);
        window.show();
            
    }
    
    public void addPartToAssociatedParts(ActionEvent event) throws IOException{
    
        
        Part part = partTable.getSelectionModel().getSelectedItem();
        currentParts.add(part);
        associatedPartsTable.setItems(currentParts);
       
      
}
    
    
    public void deletePartFromTableview(){
         //Grab the selected Part
        Part part = associatedPartsTable.getSelectionModel().getSelectedItem();
        
        //Test to see if the part is valid, if so create an alert confirming they want to delete the part.
        if (part != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText("Are you sure you want to delete " + part.getName().get() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            
            //Delete the part if yes, if no, close the box.
            if (result.get() == ButtonType.OK) {
               currentParts.remove(part);
            } else {
                alert.close();
            }
        }
    }
    
    
    
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        partNameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        partIdColumn.setCellValueFactory(cellData -> cellData.getValue().getPartID().asObject());
        partPriceColumn.setCellValueFactory(cellData -> cellData.getValue().getPrice().asObject());
        partInStockColumn.setCellValueFactory(cellData -> cellData.getValue().getInStock().asObject());
        
        associatedPartsNameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        associatedPartsIDColumn.setCellValueFactory(cellData -> cellData.getValue().getPartID().asObject());
        associatedPartsPriceColumn.setCellValueFactory(cellData -> cellData.getValue().getPrice().asObject());
        associatedPartsInStockColumn.setCellValueFactory(cellData -> cellData.getValue().getInStock().asObject());
        
        
        
        partTable.setItems(Inventory.getAllParts());
    }    
    
}
