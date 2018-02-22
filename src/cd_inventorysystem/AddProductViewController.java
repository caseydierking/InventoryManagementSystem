/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cd_inventorysystem;

import cd_inventorysystem.Models.Inventory;
import cd_inventorysystem.Models.Part;
import cd_inventorysystem.Models.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
 * FXML Controller class
 *
 * @author caseydierking
 */
public class AddProductViewController implements Initializable {

    @FXML
    private TableView<Part> partTable;
    @FXML
    private TableColumn<Part, Integer> partIdColumn;
    @FXML
    private TableColumn<Part, String> partNameColumn;
    @FXML
    private TableColumn<Part, Integer> partInStockColumn;
    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    @FXML
    private TableView<Part> associatedPartsTable;
    @FXML
    private TableColumn<Part, Integer> associatedPartsIDColumn;
    @FXML
    private TableColumn<Part, String> associatedPartsNameColumn;
    @FXML
    private TableColumn<Part, Integer> associatedPartsInStockColumn;
    @FXML
    private TableColumn<Part, Double> associatedPartsPriceColumn;

    @FXML
    private TextField productName;
    @FXML
    private TextField productPrice;
    @FXML
    private TextField productInStock;
    @FXML
    private TextField productMin;
    @FXML
    private TextField productMax;
    @FXML
    private TextField searchTextField;
    private String exceptionMessage = new String();

    private ObservableList<Part> currentParts = FXCollections.observableArrayList();

    public void saveProductButtonPushed(ActionEvent event) throws IOException {
        //Create a new product object and set variables to the text the user inputs
        Product newProduct = new Product();

        String tempProductName = productName.getText();
        String tempPrice = productPrice.getText();
        String tempInstock = productInStock.getText();
        String tempMin = productMin.getText();
        String tempMax = productMax.getText();

        
        //Set up exceptions to test if the product is valid
        try {
                exceptionMessage = Product.isProductValid(tempProductName,Double.parseDouble(tempPrice),Integer.parseInt(tempInstock),Integer.parseInt(tempMin), Integer.parseInt(tempMax), exceptionMessage);
                if (exceptionMessage.length() > 0) {
                     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                     alert.setTitle("Error Adding Product");
                     alert.setHeaderText("Error");
                     alert.setContentText(exceptionMessage);
                     alert.showAndWait();
                     exceptionMessage = "";
            } else {
                String ProductName = productName.getText();
                double price = Double.parseDouble(productPrice.getText());
                int inStock = Integer.parseInt(productInStock.getText());
                int min = Integer.parseInt(productMin.getText());
                int max = Integer.parseInt(productMax.getText());
                
                   //Set values for the product
                   newProduct.setName(ProductName);
                   newProduct.setPrice(price);
                   newProduct.setInStock(inStock);
                   newProduct.setMin(min);
                   newProduct.setMax(max);
            
                   //Create a new Array list and get parts from the associatedParts table
                  ArrayList<Part> parts = new ArrayList<>();
                  parts.addAll(associatedPartsTable.getItems());
                  newProduct.addAssociatedParts(parts);

                     if(newProduct.getAssociatedParts().size() >= 1){
                         if(price > newProduct.getPartCost()){
                            //save Product to Memory and delete the old product
                            Inventory.addProduct(newProduct);
                        
                            //Send the user back to the main page
                            Parent savedParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                            Scene addPartScene = new Scene(savedParent);

                            //Get Stage Information
                             Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                             window.setScene(addPartScene);
                              window.show();
                         } else {
                              Alert alert = new Alert(Alert.AlertType.INFORMATION);
                              alert.setTitle("Error Adding Product");
                              alert.setHeaderText("Error");
                              alert.setContentText("You can't add a product where the cost of the parts cost more!");
                              alert.showAndWait();
                         }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                     alert.setTitle("Error Adding Product");
                     alert.setHeaderText("Error");
                     alert.setContentText("You can't add a product without a part!");
                     alert.showAndWait();
                     
            }
    
               
               
            
        }
            } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("There was an Error adding a Part.");
            alert.setHeaderText("Error");
            alert.setContentText("The form contains blank fields. Please correct and try again.");
            alert.showAndWait();
        }
    
            
    }

    public void addPartToAssociatedParts(ActionEvent event) throws IOException {

        Part part = partTable.getSelectionModel().getSelectedItem();
        currentParts.add(part);
        associatedPartsTable.setItems(currentParts);

    }

    public void deletePartFromTableview() {
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

    public void cancelButtonPushed(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("Are you sure you want to cancel?");
        Optional<ButtonType> result = alert.showAndWait();

        //Delete the part if yes, if no, close the box.
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
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partNameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        partIdColumn.setCellValueFactory(cellData -> cellData.getValue().getPartID().asObject());
        partPriceColumn.setCellValueFactory(cellData -> cellData.getValue().getPrice().asObject());
        partInStockColumn.setCellValueFactory(cellData -> cellData.getValue().getInStock().asObject());

        associatedPartsNameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        associatedPartsIDColumn.setCellValueFactory(cellData -> cellData.getValue().getPartID().asObject());
        associatedPartsPriceColumn.setCellValueFactory(cellData -> cellData.getValue().getPrice().asObject());
        associatedPartsInStockColumn.setCellValueFactory(cellData -> cellData.getValue().getInStock().asObject());

        partTable.setItems(Inventory.getAllParts());

        enablePartSearch();

    }

    public void enablePartSearch() {

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

}
