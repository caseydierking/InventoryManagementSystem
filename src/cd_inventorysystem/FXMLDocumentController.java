/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cd_inventorysystem;


import cd_inventorysystem.Models.Inventory;
import static cd_inventorysystem.Models.Inventory.removePart;
import static cd_inventorysystem.Models.Inventory.removeProduct;
import cd_inventorysystem.Models.Part;
import cd_inventorysystem.Models.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
 *
 * @author caseydierking
 */
public class FXMLDocumentController implements Initializable {
    
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
    @FXML protected TextField searchTextField;
    @FXML protected TextField searchProductTextField;
    private int sourceIndex;
    
    
   
    
    
    /** 
     * This code handles the logic for when the "add Part" button is pushed.
     *
     * @param event
     */
    
    public void  addPartButtonPushed(ActionEvent event) throws IOException{
        Parent addPartViewParent = FXMLLoader.load(getClass().getResource("AddPartView.fxml"));
        Scene addPartScene = new Scene(addPartViewParent);
        
        //Get Stage Information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addPartScene);
        window.show();
    }
    
    
    /** 
     * This code handles the logic for when the "add Product" button is pushed.
     *
     * @param event
     * @throws java.io.IOException
     */
    
    public void  addProductButtonPushed(ActionEvent event) throws IOException{
        Parent addProductViewParent = FXMLLoader.load(getClass().getResource("AddProductView.fxml"));
        Scene addProductScene = new Scene(addProductViewParent);
        
        //Get Stage Information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addProductScene);
        window.show();
    }
    
  
    public void  modifyPartButtonPushed(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifyPartView.fxml"));
        Parent modifyPartViewParent = loader.load();
        Scene modifyPartScene = new Scene(modifyPartViewParent);
        
        ModifyPartViewController controller = loader.getController();
        controller.initData(partTable.getSelectionModel().getSelectedItem());

        //Get Stage Information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(modifyPartScene);
        window.show();
    }
    
    public void  modifyProductButtonPushed(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifyProductView.fxml"));
        Parent modifyProductViewParent = loader.load();
        Scene modifyProductScene = new Scene(modifyProductViewParent);
        
        ModifyProductViewController productController = loader.getController();
        productController.initProductData(productTable.getSelectionModel().getSelectedItem());

        //Get Stage Information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(modifyProductScene);
        window.show();
    }
    
    
    
    
    public void deletePartButtonPushed() {
        //Grab the selected Part
        Part part = partTable.getSelectionModel().getSelectedItem();
        
        //Test to see if the part is valid, if so create an alert confirming they want to delete the part.
        if (part != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText("Are you sure you want to delete " + part.getName().get() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            
            //Delete the part if yes, if no, close the box.
            if (result.get() == ButtonType.OK) {
               removePart(part);
            } else {
                alert.close();
            }
        }
     
    }
    
    
    public void deleteProductButtonPushed() {
        //Grab the selected Part
        Product product = productTable.getSelectionModel().getSelectedItem();
        
        //Test to see if the part is valid, if so create an alert confirming they want to delete the part.
        if (product.getAssociatedParts().size() >= 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("You can't delete a Product that has a part assigned to it");
            Optional<ButtonType> result = alert.showAndWait();
          
        } else {
            removeProduct(product);
          
        }
     
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

        
        
        enablePartSearch();
        enableProductSearch();
        
       
        
        
        
    }    
    
    
   public void enablePartSearch(){
       
       // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Part> filteredData = new FilteredList<>(Inventory.getAllParts(), p -> true);

       // 2. Set the filter Predicate whenever the filter changes.
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(part -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (part.getName().get().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches  name.
                } else if (part.getPartID().toString().contains(lowerCaseFilter)) {
                    return true; // Filter matches id
                }
                return false; // Does not match.
            });
        });

       
         // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Part> sortedData = new SortedList<>(filteredData);
    
    
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(partTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        partTable.setItems(sortedData);
        
    }
   
   
   
   public void enableProductSearch(){
       // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Product> filteredData = new FilteredList<>(Inventory.getAllProducts(), p -> true);

       // 2. Set the filter Predicate whenever the filter changes.
        searchProductTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(part -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (part.getName().get().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches  name.
                } else if (part.getProductID().toString().contains(lowerCaseFilter)) {
                    return true; // Filter matches id
                }
                return false; // Does not match.
            });
        });

       
         // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Product> sortedData = new SortedList<>(filteredData);
    
    
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(productTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        productTable.setItems(sortedData);
   }
    
    
}
