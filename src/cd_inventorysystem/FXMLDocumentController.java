/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cd_inventorysystem;


import cd_inventorysystem.Models.Inventory;
import cd_inventorysystem.Models.Inhouse;
import cd_inventorysystem.Models.Outsourced;
import cd_inventorysystem.Models.Part;
import cd_inventorysystem.Models.Product;


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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author caseydierking
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private Label label;
    @FXML private TableView<Part> partTable;
    @FXML private TableColumn<Part, Integer> partIdColumn;
    @FXML private TableColumn<Part, String> partNameColumn;
    @FXML private TableColumn<Part, Integer> partInStockColumn;
    @FXML private TableColumn<Part, Double> partPriceColumn;
    
    @FXML private TableView<Product> productTable;
    @FXML private TableColumn<Product, Integer> productIDColumn;
    @FXML private TableColumn<Product, String> productNameColumn;
    @FXML private TableColumn<Product, Integer> productInStockColumn;
    @FXML private TableColumn<Product, Double> productPriceColumn;
    
    
    
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    
    
    /**
     * This method allows the user to save a new Part
     */
    
    public void savePart(){
        Part newPart = new Inhouse(1,"testpart",1,50,1,1);

    }
    
    
    
    /** 
     * This code handles the logic for when the "add Part" button is pushed.
     *
     */
    
    public void  addPartButtonPushed(ActionEvent event) throws IOException{
        Parent addPartViewParent = FXMLLoader.load(getClass().getResource("AddPartView.fxml"));
        Scene addPartScene = new Scene(addPartViewParent);
        
        //Get Stage Information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addPartScene);
        window.show();
    }
    
    public void  modifyPartButtonPushed(ActionEvent event) throws IOException{
        Parent modifyPartViewParent = FXMLLoader.load(getClass().getResource("ModifyPartView.fxml"));
        Scene modifyPartScene = new Scene(modifyPartViewParent);
        
        //Get Stage Information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(modifyPartScene);
        window.show();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Sets up and binds the columns in the table
        partNameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        partIdColumn.setCellValueFactory(cellData -> cellData.getValue().getPartID().asObject());
        partPriceColumn.setCellValueFactory(cellData -> cellData.getValue().getPrice().asObject());
        partInStockColumn.setCellValueFactory(cellData -> cellData.getValue().getInStock().asObject());
        productNameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        productIDColumn.setCellValueFactory(cellData -> cellData.getValue().getProductID().asObject());
        productPriceColumn.setCellValueFactory(cellData -> cellData.getValue().getPrice().asObject());
        productInStockColumn.setCellValueFactory(cellData -> cellData.getValue().getInStock().asObject());
        
        
        
        
        //load some data
        partTable.setItems(Inventory.getAllParts());
        productTable.setItems(Inventory.getAllProducts());

        
        
        
    }    
    
    
}
